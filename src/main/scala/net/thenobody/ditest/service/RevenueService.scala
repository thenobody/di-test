package net.thenobody.ditest.service

/**
 * Created by antonvanco on 30/04/2015.
 */
trait RevenueService {
  def getUserRevenue: Map[User, Double]
}
