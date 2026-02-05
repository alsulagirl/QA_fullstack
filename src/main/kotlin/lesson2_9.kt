package org.example

class User(var name: String, var email: String, var pass: String, var dateOfRegistry: String)

class Order(var status: String = "ACCEPTED", var comment: String = "", var fullPrice: Double = 0.0)


fun safeApiResponse(someResponse: String?) {
    if (someResponse == null) {
        println("Данные не получены")
    } else someResponse.let {
        someResponse.replace("\",", "\",\n")
        println(someResponse)
    }
}

fun main() {
    //Задача 1
    val user = User("", "", "", "")
    user.apply {
        this.name = "Tom"
        this.email = "Tom@mail.ru"
        this.pass = "1234"
        this.dateOfRegistry = "04/02/2026"
    }
    println(
        """
        Задача 1
        name: ${user.name}
        email: ${user.email}
        pass: ${user.pass}
        dateOfRegistry: ${user.dateOfRegistry}
    """.trimIndent()
    )

    //Задача 2
    val order = with(Order()) {
        status = "PROCESSED"
        comment = "тестовый заказ"
        fullPrice = 1000.0

        println(
            """
            Задача 2
            status : ${status}
            comment : ${comment}
            fullPrice : ${fullPrice}            
        """.trimIndent()
        )
    }

    //Задача 3
    println("Задача 3")
    safeApiResponse("{\"status\": \"ok\", \"result\": \"данные\"}\n")

    //Задача 4
    val users: MutableList<User> = mutableListOf(
        User("Иван", "ivan@test.ru", "1234", "05/02/2026"),
        User("Петр", "petr@test.ru", "1234", "05/02/2026")
    )

    val newUser = User("Иван","ivan@test.ru", "1234", "05/02/2026")
        users.add(newUser).also {
            println("Отправлен запрос на создание пользователя. email: ${newUser.email}")
            println("Создается пользователь. email: ${newUser.email}")
            println("Пользователь создан. email: ${newUser.email}")
        }

}