package org.example

//Задача 2
fun main() {

    val checkSearch: Array<Int> = arrayOf(-1, 0, 1)
    println("Задача 1+2")
    val resultSimulateElementSearch1: String = simulateElementSearch(checkSearch[0]) ?: "Элемент не найден"
    println("Для ${checkSearch[0]} $resultSimulateElementSearch1")

    val resultSimulateElementSearch2: String = simulateElementSearch(checkSearch[1]) ?: "Элемент не найден"
    println("Для ${checkSearch[1]} $resultSimulateElementSearch2")

    val resultSimulateElementSearch3: String = simulateElementSearch(checkSearch[2]) ?: "Элемент не найден"
    println("Для ${checkSearch[2]} $resultSimulateElementSearch3")


    //Задача 3
    println("Задача 3")
    val serverResponse: Array<Any> = arrayOf("error", 400)
    //при проверке as? не обязательно первый аргумет брать в круглые скобки, это только загромождает
    //Если туже безопасно проверили через as? Int, то дальше уже лучше проверить на наличие значения через == null
    // val systemResponseCode = serverResponse[1] as? Int
    // if (systemResponseCode != null) {
    //     println("Код ошибки: ${serverResponse[1]}")
    //} else {
    //    println("Неизвестный код ошибки")
    //}
    if ((serverResponse[1] as? Int) is Int) {
        println("Код ошибки: ${serverResponse[1]}")
    } else println("Неизвестный код ошибки")
}

//Задача 1
//Мы еще не обговаривали, но лучше сразу привыкнуть к правильному форматированию if
//Есть вариант записи в одну строку если она умещается в огороренную длину, тогда мы фигурные скобки не используем
//if (searchId > 0) return "Элемент №$searchId" else return null
// Второй вариант классический, каждый блок выделяется фигурными скобками и с новой строки
//if (searchId > 0) {
//    return "Элемент №$searchId"
//} else {
//    return null
//}
fun simulateElementSearch(searchId: Int): String? {
    if (searchId > 0) {
        return "Элемент №$searchId"
    } else {
        return null
    }
}

