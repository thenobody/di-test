# Scala dependency injection example

This project demonstrates the usage of pure Scala dependency injection and unit testing. This approach was inspired by DI described at [http://www.michaelpollmeier.com/2014/06/29/simple-dependency-injection-scala/](http://www.michaelpollmeier.com/2014/06/29/simple-dependency-injection-scala/).

In this approach, we use constructor dependency passing rather than defining them on class init. This enables compile time checking of dependency mocks in unit tests and promotes dependency usage visibility to the developer in general.

## The container
```
net/thenobody/ditest/container/package.scala
```
The container defines two types of instantiation:

1. *singleton*  

```scala
object RevenueServiceInstance extends RedisRevenueService(
  config.getString("ditest.redis.host"),
  config.getInt("ditest.redis.database"),
  config.getInt("ditest.redis.timeout"),
  UserServiceInstance
)
```
The instance of `RevenueServiceInstance` is created only once (when requested for the first time).

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
A new instance is created everytime it is requested.

## Config
The project uses [typesafe/Config](https://github.com/typesafehub/config) and by default reads the configuration parameters from `src/main/resources/application.conf`. This can be overriden by passing `config.file` system parameter when running from console:

```sh
$ java -jar path/to/di-test.jar net.thenobody.ditest.Main -Dconfig.file=/path/to/application.conf
```

## Usage
First, the container needs to be imported:

```
import net.thenobody.ditest.container._
```

Afterwards, the instances can be obtained by:

```scala
val revenueService1: RevenueService = RevenueServiceInstance   // returns singleton
val revenueService2: RevenueService = RevenueServiceProvider() // returns new instance on every invocation
```

## Unit tests
Example unit test can be found in [src/test/scala/net/thenobody/ditest/service/RedisRevenueServiceTest.scala](src/test/scala/net/thenobody/ditest/service/RedisRevenueServiceTest.scala) which uses [Mockito](https://github.com/mockito/mockito). The mocks are passed to the tested class as constructor arguments (compile time dependency check).