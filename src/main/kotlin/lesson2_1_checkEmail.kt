package org.example

fun main() {
   val invalidEmails: Array<String> = arrayOf("testEmail", "@testEmail.ru", "testEmail")

   val errorMessage : String = "Неверный формат email"

   //1. Тест 1
   val currentInvalidEmail = invalidEmails[0]

   println("""
      Введен Email : $currentInvalidEmail
      Система вернула ошибку : $errorMessage
      Длина введенного email : ${currentInvalidEmail.length}
      Содержит ли тект ошибки слово \"формат\" : ${errorMessage.contains("формат") }
   """)

}