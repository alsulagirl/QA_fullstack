package org.example

import jdk.jshell.Snippet.Status


fun main() {
    //Задача 1
    println("\nЗадача 1")
    val prices: List<Double> = listOf(99.99, 25.50, 75.00, 300.00, 49.99)
    println("Максимальная цена : ${prices.max()}")
    println("Минимальная цена : ${prices.min()}")
    println("Средняя цена : ${prices.average()}")
    println("Цены выше 50.0 : ${prices.filter { it > 50.0 }}")

    //Задача 2
    println("\nЗадача 2")
    val users = listOf("alice", "bob", "charlie", "david", "eve")
    val upperCaseUsers = users.map { "User: " + it.uppercase() }
    println("Преобразованный вид: $upperCaseUsers")
    println("Первое имя выше 4 символов: ${users.firstOrNull { it.length > 4 }}")
    println("Имена, сгруппированные по длине: ${users.groupBy { it.length }}")

    //Задача 3
    println("\nЗадача 3")
    val apiResponse = mapOf(
        "data" to listOf(
            mapOf("id" to 1, "status" to "active"),
            mapOf("id" to 2, "status" to "inactive"),
            mapOf("id" to 3, "status" to "active")
        ),
        "total" to 3
    )
    val activeItems = (apiResponse["data"]  as? List<Map<String, Any>>)
        ?.filter { it["status"] == "active" }

        println("Активные пользователи: ${(activeItems?.filter { it.contains("id") })}}")
        println("Активных пользователей : ${(activeItems?.filter { it.contains("id") })?.size}")

}










