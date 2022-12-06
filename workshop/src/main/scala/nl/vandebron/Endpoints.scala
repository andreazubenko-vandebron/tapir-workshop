package nl.vandebron

import io.circe.generic.auto._
import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._

object Endpoints {
  // GET /cats
  val getAllCats = endpoint.get.in("cats").in("all-cats")

  // GET /cats/{name}
  val getCat = endpoint.get.in("cats").in("just-one-cat")

  // POST /cats
  val postCat = endpoint.post.in("cats").in("create-one")
}
