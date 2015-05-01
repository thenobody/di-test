package net.thenobody.ditest.service

/**
 * Created by antonvanco on 30/04/2015.
 */
class RedisRevenueService(
  val redisHost: String,
  val redisDatabase: Int,
  val redisTimeout: Int,
  val userService: UserService
) extends RevenueService {

  println("RedisRevenueService is instantiating")

  override def getUserRevenue: Map[User, Double] = userService.getUsers.map(user => user -> 1000.0).toMap
}
