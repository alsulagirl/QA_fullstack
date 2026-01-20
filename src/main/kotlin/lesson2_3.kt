package org.example

fun main() {
    //Задача 1
    println("Задача 1")
    var serverStatus = "processing"
    var i=1

    while(serverStatus!="success"){
        if (i==4) {
            serverStatus = "success"
        } else {
            i++
        }
        println("Статус: $serverStatus")
    }

    //Задача 2
    println("Задача 2")
    i = 0 //Непонятно для чего это присваивание
    for(i in 1..5){
        when {
            i<=3 -> println("Тест $i: В процессе")
            i==4 -> println("Тест $i: Провален")
            i==5 -> println("Тест $i: Успех")
        }
    }

    //Задача 3
    println("Задача 3")

    println("Тест 1 : ${testsComplexity(4, 1)}")
    println("Тест 2 : ${testsComplexity(10, 3)}")
    println("Тест 3 : ${testsComplexity(30, 4)}")
    println("Тест 4 : ${testsComplexity(40, 5)}")
    println("Тест 5 : ${testsComplexity(40, 6)}")

}

fun testsComplexity(duration:Int, complexity:Int):String {
    
    // У нас when имеет возвращаемое значение, поэтому лучше в return возращать сразу то что определяется в when
    //В задании не указано конкретно, но в случае если не удалось определить тип теста, то лучше выводить понятное описание, а не просто error
    //Если ты решила выводить префикс для каждого случая и он одинаков, лучше сохранять его в константу
    //val prefix = "duration $duration , complexity $complexity : "
    // return when {
    //     duration<10 -> "$prefix Быстрый тест"
    //     (duration in 10..30) and (complexity in 1..3) -> "$prefix Стандартный тест"
    //     (duration in 10..30) and (complexity in 4..5) -> "$prefix Сложный тест"
    //     duration>30 -> "$prefix Длительный тест"
    //     else -> "Не удалось определить тип теста для длительности $duration и сложности $complexity"
    // }
    
    when {
        duration<10 -> return "duration $duration , complexity $complexity : Быстрый тест"
        (duration in 10..30) and (complexity in 1..3) -> return "duration $duration , complexity $complexity : Стандартный тест"
        (duration in 10..30) and (complexity in 4..5) -> return "duration $duration , complexity $complexity : Сложный тест"
        duration>30 -> return "duration $duration , complexity $complexity : Длительный тест"
        complexity !in 1..5 -> return  "duration $duration , complexity $complexity : error"
        else -> return "error"
    }
}

