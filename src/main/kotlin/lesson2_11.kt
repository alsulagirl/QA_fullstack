package org.example

fun calculate(a: Double, b: Double, operator: String) {
when (operator) {
        "*" -> return println("${a * b}")
        "-" -> return println("${a - b}")
        "+" -> return println("${a + b}")
        "/" -> if (b == 0.0) {
            throw ArithmeticException("Ошибка: Делить на ноль нельзя")
        } else return println("${a / b}")
        else -> throw IllegalArgumentException("Указан неверный оператор $operator")
    }
}

// Пример использования
fun main() {
    calculate(10.0, 0.0, "/") // должно выбросить ArithmeticException
    calculate(10.0, 5.0, "&") // должно выбросить IllegalArgumentException
    calculate(10.0, 2.0, "+") // должно вернуть 12.0
}
