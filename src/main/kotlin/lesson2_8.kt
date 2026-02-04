package org.example

//Общий класс для Задачи 1 и Задачи 2
class Field<T>(var item: T)

//Задача 1
fun <T> validateField(checkField: T?): Boolean {
    when (checkField) {
        is String -> return checkField.isBlank()
        else -> return (checkField == null)
    }
}

//Задача 2
fun <T> countElements(items: List<T>): Int {
    return items.size
}

//Задача 3
//Обобщенный класс Basket
class Basket<T>(var items: MutableList<T>) {

    fun addItem(newItem: T) {
        items.add(items.lastIndex, newItem)
    }

    fun getAllItems(): List<T> {
        return items
    }
}

interface Weighable {
    val weight: Double
}

interface Valuable {
    val value: Int
}

fun <T> Basket<T>.totalWeight(): Double where T : Weighable {
    return items.sumOf { it.weight }
}

fun <T> Basket<T>.totalValue(): Int where T : Valuable{
    return items.sumOf { it.value }
}


fun main() {
    //Задача 1
    println("\nЗадача 1")
    val field = Field(listOf("", 1, null, "1", true))
    for (i in field.item) {
        println("$i is null or empty? : ${validateField(i)} ")
    }

    //Задача 2
    println("\nЗадача 2")
    val itemsNumber = Field(listOf(1, "2", null))
    println("Размер списка: ${countElements(itemsNumber.item)}")

    //Задача 3
    println("\nЗадача 3")
    data class Book(override val value: Int) : Valuable
    data class Apple(override val value: Int, override val weight: Double) : Weighable, Valuable

    val bookBasket = Basket<Book>(mutableListOf(Book(100)))
    bookBasket.addItem(Book(200))
    bookBasket.addItem(Book(300))

    println("bookBasketValue: ${bookBasket.totalValue()}")
    //println("bookBasketWeight: ${bookBasket.totalWeight()}")

    val appleBasket = Basket<Apple>(mutableListOf(Apple(200,0.2)))
    appleBasket.addItem(Apple(100,0.1))

    println("appleBasketValue: ${appleBasket.totalValue()}")
    println("appleBasketWeight: ${appleBasket.totalWeight()}")


}