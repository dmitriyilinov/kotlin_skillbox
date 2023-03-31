package corutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.math.BigInteger
import kotlin.random.Random
import kotlin.random.asJavaRandom

/* В coroutineScope можно запускать сразу несколько корутин и все они будут производить свои вычисления параллельно.
* А так же внутри корутины, можно запускать другие корутины.*/

suspend fun main() {

    coroutineScope {
        println("Запускаем несколько корутин")

        // запускаем корутину с таймером
        launch {
            println("Корутина с таймером запущена...")

            // внутри этой корутины запускаем другую для вычисления первого большого числа
            launch {
                println("Корутина рандомного получения первого большого числа начала работу, число вычисляется...")
                val firstNumber = BigInteger.probablePrime(4400, Random.asJavaRandom())
                println("Корутина рандомного получения первого большого числа закончила вычисление $firstNumber")
            }

            // запускаем таймер не дожитаясь расчёта первого числа
            countdown()
            println("Корутина с таймером закончила обратный отсчёт")
        }

        // запускаем третью корутину, для вычисления второго большого числа
        launch {
            println("Корутина рандомного получения второго большого числа начала работу, число вычисляется...")
            val secondNumber = BigInteger.probablePrime(4200, Random.asJavaRandom())
            println("Корутина рандомного получения второго большого числа закончила вычисление $secondNumber")
        }
    }

    println("Все CoroutineScope и корутины завершили свои вычисления. Программа завершена.")
}

