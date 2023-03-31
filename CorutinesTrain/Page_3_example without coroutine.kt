package corutines

import kotlinx.coroutines.delay
import java.math.BigInteger
import kotlin.random.Random
import kotlin.random.asJavaRandom

/*На очень простом примере, рассмотрим применение корутин. Напишем небольшой код, который будет расшифровывать сообщение
и допустим сгенерируем какое-нибудь большое число. Обратим внимания, что программа написана линейно в одном потоке.
И при вычислении большого числа, вся программа как бы стопориться и не может выполняться дальше, пока число не
будет рассчитано.*/

suspend fun main() {

    val text = "7рфЯ7#ёулрмрсузг7зПапгбм]йжцъ]имосржлщ"
    println(text)

    // запускаем функцию расшифровки первой части
    val fistPart = decryptFist(text)

    /* запускаем функцию расчёта числа (требуется время для ёё расчёта), и обращаем внимание, что далее код не будет
    пока число не будет рассчитано*/
    calculateNumber()

    // и только после расчёта числа, будет запущено расшифровка второй части
    val secondPart = decryptSecond(text)
    println("Сообщение расшифровано: ${fistPart + secondPart}")

}

// Теперь переходим на следующую страницу и выполним вычисление большого числа с помощью корутины.

suspend fun decryptFist(text: String): String {
    println("Началась расшифровка первой части:")
    delay(500)

    var firstPart = text.take(text.length / 2)
    println(firstPart)
    delay(500)

    firstPart = firstPart.replace('7', '"')
    println(firstPart)
    delay(500)

    firstPart = firstPart.map { char -> char - 2 }.joinToString("")
    println(firstPart)
    delay(500)

    firstPart = firstPart.reversed()
    println(firstPart)
    delay(500)

    println("Расшифровка первой части завершена\n")
    delay(500)
    return firstPart
}

suspend fun decryptSecond(text: String): String {
    println("Началась расшифровка второй части:")
    delay(500)

    var secondPart = text.takeLast(text.length / 2)
    println(secondPart)
    delay(500)
    secondPart = secondPart.map { char -> char + 2 }.joinToString("")
    println(secondPart)
    delay(500)

    secondPart = secondPart.replace('_', ' ')
    println(secondPart)
    delay(500)

    println("Расшифровка второй части завершена\n")
    delay(500)
    return secondPart

}

suspend fun calculateNumber() {
    println("Началось вычисление большого числа".uppercase())
    val firstNumber = BigInteger.TWO.pow(4400)
    val secondNumber = BigInteger.probablePrime(4200, Random.asJavaRandom())
    val resultNumber = firstNumber / secondNumber
    // поставил здесь delay просто для дополнительной задержки, что бы еще больше замедлить выполнение кода
    delay(1000)
    println("Вычисление числа закончено: $resultNumber")
}