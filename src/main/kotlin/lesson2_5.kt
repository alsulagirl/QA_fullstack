package org.example

fun main() {
    //Задача 3
    val logTestStart : () -> Unit = {  println("Тест запущен. Время запуска теста: ${java.time.LocalTime.now()}") }

    //Задача 1
    val testNumber: Array<Int> = arrayOf(0, 1, -1)
    //Круглые скобки в определении лямбды лишние - поправила
    val isPositive: (Int) -> Boolean = { number -> number > 0 }

    for (currentNumberValue in testNumber) {
        logTestStart()
        println("Число $currentNumberValue - ${if (isPositive(currentNumberValue)) "положительное" else "неположительное"}")
    }

    //Задача 2
    val isValidPhone = { testPhone: String ->
        //contains просто вхождение проверяет, нам в начале смотерть - поправила
        testPhone.substring(0,2).equals("+7") && testPhone.substring(2)
            .all { it.isDigit() } && (testPhone.count { it.isDigit() } == 11)
    }

    logTestStart()
    val invalidPhone = "+700000000022"
    println("$invalidPhone - ${if (isValidPhone(invalidPhone)) "V" else "X"}")

    logTestStart()
    val correctPhone = "+70000000002"
    println("$correctPhone - ${if (isValidPhone(correctPhone)) "V" else "X"}")
}


