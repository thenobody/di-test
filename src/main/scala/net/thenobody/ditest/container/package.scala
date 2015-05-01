package net.thenobody.ditest

import javax.sql.DataSource

import net.thenobody.ditest.service.{RedisRevenueService, MySqlUserService}
import net.thenobody.ditest.util.DummyDataSource
import com.typesafe.config.ConfigFactory
import org.flywaydb.core.Flyway

/**
 * Created by antonvanco on 01/05/2015.
 */
package object container {

  val config = ConfigFactory.load()

  object UserServiceInstance extends MySqlUserService(FlywayDataSourceInstance())

  object RevenueServiceInstance extends RedisRevenueService(
    config.getString("ditest.redis.host"),
    config.getInt("ditest.redis.database"),
    config.getInt("ditest.redis.timeout"),
    UserServiceInstance
  )

  object RevenueServiceProvider {
    def apply(): RedisRevenueService = new RedisRevenueService(
      config.getString("ditest.redis.host"),
      config.getInt("ditest.redis.database"),
      config.getInt("ditest.redis.timeout"),
      UserServiceInstance
    )
  }

  object DataSourceInstance extends DummyDataSource(
    config.getString("ditest.mysql.host"),
    config.getString("ditest.mysql.database"),
    config.getString("ditest.mysql.username"),
    config.getString("ditest.mysql.password")
  )

  object FlywayDataSourceInstance {
    def apply(): DataSource = {
      val flyway = new Flyway
      flyway.setDataSource(DataSourceInstance)

      println("running flyway migration")
      // uncomment for actual flyway migration
      // flyway.migrate()

      DataSourceInstance
    }
  }

}
