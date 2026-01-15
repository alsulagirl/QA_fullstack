package org.example

//Задача 2
fun main() {

    val checkSearch : Array<Int> = arrayOf(-1,0,1)
    println("Задача 1+2")
    val resultSimulateElementSearch1 : String = simulateElementSearch(checkSearch[0]) ?: "Элемент не найден"
    println("Для ${checkSearch[0]} $resultSimulateElementSearch1")

    val resultSimulateElementSearch2 : String = simulateElementSearch(checkSearch[1]) ?: "Элемент не найден"
    println("Для ${checkSearch[1]} $resultSimulateElementSearch2")

    val resultSimulateElementSearch3 : String = simulateElementSearch(checkSearch[2]) ?: "Элемент не найден"
    println("Для ${checkSearch[2]} $resultSimulateElementSearch3")


    //Задача 3
    println("Задача 3")
    val serverResponse: Array<Any> = arrayOf("error", 400)
    if (((serverResponse[1]) as? Int) is Int) { println("Код ошибки: ${serverResponse[1]}")}
    else println("Неизвестный код ошибки")
}

//Задача 1
fun simulateElementSearch(searchId:Int) :String? {
    if (searchId > 0) {return "Элемент №$searchId"}
    else return null
}

