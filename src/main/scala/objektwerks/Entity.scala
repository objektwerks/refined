package objektwerks

import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*

final case class Valid(map: Map[String, String]):
  def isValid: Boolean = map.isEmpty

sealed trait Entity:
  val id: Long

object Entity:
  given JsonValueCodec[Entity] = JsonCodecMaker.make[Entity]
  given JsonValueCodec[Account] = JsonCodecMaker.make[Account]
  given JsonValueCodec[Pool] = JsonCodecMaker.make[Pool]
  given JsonValueCodec[Cleaning] = JsonCodecMaker.make[Cleaning]
  given JsonValueCodec[Measurement] = JsonCodecMaker.make[Measurement]
  given JsonValueCodec[Chemical] = JsonCodecMaker.make[Chemical]

final case class Account(id: Long,
                         license: String,
                         emailAddress: String,
                         pin: String,
                         activated: Long,
                         deactivated: Long) extends Entity

final case class Pool(id: Long,
                      accountId: Long,
                      name: String, 
                      built: Int,
                      volume: Int,
                      unit: UnitOfMeasure) extends Entity

final case class Cleaning(id: Long :| GreaterEqual[0],
                          poolId: Long :| Greater[0],
                          brush: Boolean,
                          net: Boolean,
                          skimmerBasket: Boolean,
                          pumpBasket: Boolean,
                          pumpFilter: Boolean,
                          vacuum: Boolean,
                          cleaned: Long :| Greater[0]) extends Entity

final case class Measurement(id: Long :| GreaterEqual[0],
                             poolId: Long :| Greater[0],
                             totalChlorine: Int :| Interval.Closed[1, 5],
                             freeChlorine: Int :| Interval.Closed[1, 5],
                             combinedChlorine: Double :| Interval.Closed[0.0, 0.5],
                             ph: Double :| Interval.Closed[6.2, 8.4],
                             calciumHardness: Int :| Interval.Closed[250, 500],
                             totalAlkalinity: Int :| Interval.Closed[80, 120],
                             cyanuricAcid: Int :| Interval.Closed[30, 100],
                             totalBromine: Int :| Interval.Closed[2, 10],
                             salt: Int :| Interval.Closed[2700, 3400],
                             temperature: Int :| Interval.Closed[50, 100],
                             measured: Long :| Greater[0]) extends Entity

final case class Chemical(id: Long :| GreaterEqual[0],
                          poolId: Long :| Greater[0],
                          typeof: TypeOfChemical,
                          amount: Double :| Greater[0.0],
                          unit: UnitOfMeasure,
                          added: Long :| Greater[0]) extends Entity

enum UnitOfMeasure:
  case gl, l, lb, kg, tablet

enum TypeOfChemical(val display: String):
  case LiquidChlorine extends TypeOfChemical("Liquid Chlorine")
  case Trichlor extends TypeOfChemical("Trichlor")
  case Dichlor extends TypeOfChemical("Dichlor")
  case CalciumHypochlorite extends TypeOfChemical("Calcium Hypochlorite")
  case Stabilizer extends TypeOfChemical("Stabilizer")
  case Algaecide extends TypeOfChemical("Algaecide")
  case MuriaticAcid extends TypeOfChemical("Muriatic Acid")
  case Salt extends TypeOfChemical("Salt")
