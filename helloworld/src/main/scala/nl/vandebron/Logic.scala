package nl.vandebron

import scala.concurrent.Future

object Logic {
  def helloLogic(name: String): Future[String] =
    Future.successful(s"Hello, $name!")
}
