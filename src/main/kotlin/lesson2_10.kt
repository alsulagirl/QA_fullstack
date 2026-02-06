package org.example

import java.util.*

@Deprecated("Use newFunction instead", ReplaceWith("printEmployeeName()"))
fun printName(name:String){
    println("Старый вывод : $name")
}


fun printEmployeeName(name:String){
    println("Новый вывод : ${name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}")
}

fun main(){
    val testName = "tom"
    printName(testName)

    printEmployeeName(testName)
}