package nl.vandebron

import scala.concurrent.Future

object Logic {

  val cats = Data.cats

  def getAllCats = (_: Any) => Future.successful(())
  def getCat = (_: Any) => Future.successful(())
  def createCat = (_: Any) => Future.successful(())
}
