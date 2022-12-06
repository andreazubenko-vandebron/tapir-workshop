package nl.vandebron.domain

final case class Cat private (name: String, age: Int, slave: Option[Human])

object Cat {
  def apply(name: String, age: Int): Cat = new Cat(name, age, slave = None)
  def apply(name: String, age: Int, slave: Human): Cat = new Cat(name, age, Some(slave))
}
