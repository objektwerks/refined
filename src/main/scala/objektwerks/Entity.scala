package objektwerks

import eu.timepit.refined.*
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto.*
import eu.timepit.refined.numeric.*

final case class Valid(map: Map[String, String]):
  def isValid: Boolean = map.isEmpty

opaque type EntityId = Long Refined Greater[-1]
object EntityId:
  def apply(id: Long): EntityId = id

sealed trait Entity:
  val id: EntityId

final case class Account(id: EntityId,
                         license: String,
                         emailAddress: String,
                         pin: String,
                         activated: Long,
                         deactivated: Long) extends Entity

final case class Pool(id: EntityId,
                      accountId: Long,
                      name: String, 
                      built: Int,
                      volume: Int,
                      unit: UnitOfMeasure) extends Entity

final case class Cleaning(id: EntityId,
                          poolId: EntityId,
                          brush: Boolean,
                          net: Boolean,
                          skimmerBasket: Boolean,
                          pumpBasket: Boolean,
                          pumpFilter: Boolean,
                          vacuum: Boolean,
                          cleaned: Long) extends Entity

final case class Measurement(id: EntityId,
                             poolId: EntityId,
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

final case class Chemical(id: EntityId,
                          poolId: EntityId,
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