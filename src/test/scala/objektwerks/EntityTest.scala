package objektwerks

import java.time.Instant
import java.util.UUID

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class EntityTest extends AnyFunSuite with Matchers:
  test("refine"):
    val account = Account(id = 1, // Fails here; and with all EntityId fields!
                          license = UUID.randomUUID.toString,
                          emailAddress = "emailaddress@email.com",
                          pin = "1a2b3c!",
                          activated = Instant.now.getEpochSecond,
                          deactivated = 0)

    val pool = Pool(id = 1,
                    accountId = account.id,
                    name = "blue", 
                    built = 2022,
                    volume = 10000,
                    unit = UnitOfMeasure.gl)

    val cleaning = Cleaning(id = 1,
                            poolId = pool.id,
                            brush = true,
                            net = true,
                            skimmerBasket = true,
                            pumpBasket = true,
                            pumpFilter = true,
                            vacuum = true,
                            cleaned = Instant.now.getEpochSecond)

    val measurement = Measurement(id = 1,
                                  poolId = pool.id,
                                  totalChlorine = 3,
                                  freeChlorine = 3,
                                  combinedChlorine = 0.3,
                                  ph = 7.4,
                                  calciumHardness = 300,
                                  totalAlkalinity = 100,
                                  cyanuricAcid = 65,
                                  totalBromine = 6,
                                  salt = 3100,
                                  temperature = 80,
                                  measured = Instant.now.getEpochSecond)

    val chemical = Chemical(id = 1,
                            poolId = pool.id,
                            typeof = TypeOfChemical.LiquidChlorine,
                            amount = 2.5,
                            unit = UnitOfMeasure.gl,
                            added = Instant.now.getEpochSecond)
