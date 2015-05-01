# Scala dependency injection example

This project demonstrates usage of pure Scala dependency injection and unit testing. This approach was inspired by DI described at [http://www.michaelpollmeier.com/2014/06/29/simple-dependency-injection-scala/](http://www.michaelpollmeier.com/2014/06/29/simple-dependency-injection-scala/)

## The container
```
net/thenobody/ditest/container/package.scala
```
The container defines two types of instantiations:

1. *singleton*  

```scala
object RevenueServiceInstance extends RedisRevenueService(
  config.getString("ditest.redis.host"),
  config.getInt("ditest.redis.database"),
  config.getInt("ditest.redis.timeout"),
  UserServiceInstance
)
```
The instance of `RevenueServiceInstance` is created only once (when requested for the first time)

2. *prototype*

```scala
object RevenueServiceProvider {
  def apply(): RedisRevenueService = new RedisRevenueService(
    config.getString("ditest.redis.host"),
    config.getInt("ditest.redis.database"),
    config.getInt("ditest.redis.timeout"),
    UserServiceInstance
  )
}
```
A new instance is created everytime it is requested

## Usage
First, the container needs to be imported

```
import net.thenobody.ditest.container._
```