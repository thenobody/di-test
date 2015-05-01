package net.thenobody.ditest.service

/**
 * Created by antonvanco on 30/04/2015.
 */
trait UserService {
  def getUsers: Iterable[User]
}

case class User(id: String)
