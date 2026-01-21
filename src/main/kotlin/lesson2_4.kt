package org.example

fun main() {
    //Задача 3
    val actualCode = simulateApiRequest()
    checkStatus(200, actualCode)


}
    //Задача 1
fun checkStatus(expectedCode: Int, actualCode:Int) {
   println("Ожидаемый код: $expectedCode, Фактический код: $actualCode")
}

    //Задача 2
fun simulateApiRequest(): Int {
    return listOf(200,400,500).random()
}
