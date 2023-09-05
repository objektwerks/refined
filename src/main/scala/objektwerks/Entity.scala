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

final case class Cleaning(id: Long,
                          poolId: Long,
                          brush: Boolean,
                          net: Boolean,
                          skimmerBasket: Boolean,
                          pumpBasket: Boolean,
                          pumpFilter: Boolean,
                          vacuum: Boolean,
                          cleaned: Long) extends Entity

final case class Measurement(id: Long,
                             poolId: Long,
                             totalChlorine: Int,
                             freeChlorine: Int,
                             combinedChlorine: Double,
                             ph: Double,
                             calciumHardness: Int,
                             totalAlkalinity: Int,
                             cyanuricAcid: Int,
                             totalBromine: Int,
                             salt: Int,
                             temperature: Int,
                             measured: Long) extends Entity

final case class Chemical(id: Long,
                          poolId: Long,
                          typeof: TypeOfChemical,
                          amount: Double,
                          unit: UnitOfMeasure,
                          added: Long) extends Entity

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
