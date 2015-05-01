package net.thenobody.ditest.util

import java.io.PrintWriter
import java.sql.Connection
import java.util.logging.Logger
import javax.sql.DataSource

/**
 * Created by antonvanco on 01/05/2015.
 */
class DummyDataSource(
 val host: String,
 val database: String,
 val username: String,
 val password: String
) extends DataSource {

  println(s"Establishing connection mysql://${username}:${password}@${host}/${database}")

  def getConnection: Connection = ???

  def getConnection(username: String, password: String): Connection = ???

  def unwrap[T](iface: Class[T]): T = ???

  def isWrapperFor(iface: Class[_]): Boolean = ???

  def setLogWriter(out: PrintWriter): Unit = ???

  def getLoginTimeout: Int = ???

  def setLoginTimeout(seconds: Int): Unit = ???

  def getParentLogger: Logger = ???

  def getLogWriter: PrintWriter = ???
}
