package org.example

import jdk.jshell.Snippet.Status
import java.util.UUID

//Задача 1
enum class HttpStatus(val code: Int, val description: String) {
    OK(200, "Success"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_ERROR(500, "Internal Server Error");

    fun isSuccess(): Boolean = code in 200..299
}

//Задача 2
fun String.toSlug(): String {
    return (this.lowercase()).replace(' ', '-')
}

//Задача 3
object DbConfig {
    const val BASE_URL = "testurl"
    lateinit var userLogin: String
    lateinit var userPass: String


    fun initConnect(userLogin: String, userPass: String) {
        this.userLogin = userLogin
        this.userPass = userPass
        println("Подключение успешно выполняется")
    }

    fun checkConnect() {
        println("Подключение к $BASE_URL завершено")
    }

}


//Задача 4
interface ApiClient {
    val baseEndpoint: String

    fun get(id: UUID) {
        println("Запрос с $id отправлен $baseEndpoint")
    }

    fun post(body: Any) {
        println("Запрос с $body отправлен $baseEndpoint")
    }
}

abstract class AbstractApiClient() : ApiClient {
}

class TestBaseRequest() : AbstractApiClient() {
    override val baseEndpoint: String = "http://base.ru/"
}

class TestRequest() : AbstractApiClient() {
    override val baseEndpoint: String = "http://test.ru/"
    override fun post(body: Any) {
        println("Отправлен запрос $baseEndpoint\n" + "С телом запроса: $body")
    }

}


fun main() {
    //Задача 1
    println("\nЗадача 1")
    val testResponse = HttpStatus.OK
    println("${testResponse.code}, ${testResponse.description}")

    //Задача 2
    println("\nЗадача 2")
    val testString = "Test string"
    println(testString.toSlug())

    //Задача 3
    println("\nЗадача 3")
    DbConfig.initConnect("myLogin", "myPassword")
    DbConfig.checkConnect()

    //Задача 4
    println("\nЗадача 4")
    println("\nclass getRequest")
    val testBaseRequest = TestBaseRequest()
    testBaseRequest.post(
        """
{
    "name": "Иван Иванов",
    "email": "ivan@example.com",
    "age": 25,
    "isActive": true
}
""".trimIndent()
    )
    testBaseRequest.get(UUID.randomUUID())


    println("\nclass testRequest")
    val testRequest = TestRequest()
    testRequest.post(
        """
{
    "name": "Иван Петров",
    "age": 26
}
""".trimIndent()
    )
    testRequest.get(UUID.randomUUID())


}










