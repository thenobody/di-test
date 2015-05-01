package net.thenobody.ditest.service

import javax.sql.DataSource

/**
 * Created by antonvanco on 30/04/2015.
 */
class MySqlUserService(val dataSource: DataSource) extends UserService {
  println("MySqlUserService is instantiating")

  override def getUsers: Iterable[User] = Seq(
    User("id1"),
    User("id2"),
    User("id3")
  )
}
