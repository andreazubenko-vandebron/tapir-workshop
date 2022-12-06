package nl.vandebron

import nl.vandebron.domain.{Cat, Human}

object Data {

  private val andrea = Human("Andrea")
  private val martin = Human("Martin")

  var cats = Seq(
    Cat("meowy", 3),
    Cat("kat", 17, martin),
    Cat("bacon", 4, andrea),
    Cat("snowy", 7, martin),
    Cat("mr. christmas", 10)
  )

}
