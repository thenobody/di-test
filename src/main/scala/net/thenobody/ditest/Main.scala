package net.thenobody.ditest

import net.thenobody.ditest.service.{User, RevenueService}

import net.thenobody.ditest.container._

/**
 * Created by antonvanco on 30/04/2015.
 */
object Main {

  def main(args: Array[String]): Unit = {
    val revenueService1: RevenueService = RevenueServiceInstance

    revenueService1.getUserRevenue.foreach {
      case (user: User, revenue: Double) => println(s"${user} - ${revenue}")
    }

    val revenueService2: RevenueService = RevenueServiceProvider()

    revenueService2.getUserRevenue.foreach {
      case (user: User, revenue: Double) => println(s"${user} - ${revenue}")
    }

  }

}
