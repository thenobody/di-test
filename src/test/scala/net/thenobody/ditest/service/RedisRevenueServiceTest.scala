package net.thenobody.ditest.service

import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.mock.MockitoSugar

import org.mockito.Mockito.when

/**
 * Created by antonvanco on 30/04/2015.
 */
class RedisRevenueServiceTest extends FlatSpec with Matchers with MockitoSugar {

  "RedisRevenueService" should "do essentially nothing" in {
    val redisHost = "fake.host"
    val redisDatabase = 42
    val redisTimeout = 1000

    val users = Seq(User("fake1"), User("fake2"))
    val mockUserService = mock[UserService]
    when(mockUserService.getUsers).thenReturn(users)

    val expResult = users.map(user => user -> 1000.0).toMap

    val instance = new RedisRevenueService(redisHost, redisDatabase, redisTimeout, mockUserService)
    val result = instance.getUserRevenue

    result should be { expResult }
  }

}
