package corutines

import kotlinx.coroutines.*
import java.math.BigInteger
import kotlin.random.Random
import kotlin.random.nextInt

/*В случае прерывания suspend функции генерируются исключения типа CancellationException.
И в самой корутине мы можем перехватить это исключение, чтобы обработать отмену корутины.

Рассмотрим простейший пример. Создадим две корутину которые будут вычислять большое число (умножая изначальное число
на 2 какое-то количество раз). Если через пару секунд после запуска корутины она не рассчитало число, то будет
происходить ошибка, которую мы отловим с помощью try catch. Другая корутина будет выводить таймер
обратного отчёта, пока проводятся вычисления.*/

suspend fun main() {
    // случайные параметры функции multipliedByTwo для первой корутины
    val firstNumber = Random.nextInt(2..20).toBigInteger()
    val firstIteration = (250000..350000).random()

    // случайные параметры функции multipliedByTwo для второй корутины
    val secondNumber = Random.nextInt(2..20).toBigInteger()
    val secondIteration = (250000..350000).random()

    coroutineScope {
        println("Начинаем вычисления")

        // первая корутина с билдером async, результат будет помещен в Deferred и мы сможем вызвать его через метод await()
        val calculation1 = async {
            try {
                // отводится времени для вычисления две с половиной секунды
                withTimeout(2500) {
                    multipliedByTwo(number = firstNumber, iteration = firstIteration)
                }
                // обработать ошибку
            } catch (e: CancellationException) {
                "Число $firstNumber умноженное на 2 $firstIteration раз = затрачено слишком много времени. " +
                        "Результат не получен"
            }
        }

        val calculation2 = async {
            try {
                withTimeout(2500) {
                    multipliedByTwo(number = secondNumber, iteration = secondIteration)
                }
            } catch (e: CancellationException) {
                "Число $secondNumber умноженное на 2 $secondIteration раз = затрачено слишком много времени. " +
                        "Результат не получен"
            }
        }

        // пока calculation1 или calculation2 активны, выводится таймер обратного отсчёта
        launch {
            var counter = 10
            while (calculation1.isActive || calculation2.isActive) {
                counter--
                println(counter)
                delay(300)
            }
        }

        // выводим в консоль результат вычислений
        println("\n ${calculation1.await()} \n ${calculation2.await()}")
    }
}

// функция расчёта числа, принимает входные параметры исходного числа и количество итераций для цикла
suspend fun multipliedByTwo(number: BigInteger, iteration: Int): String {
    var resultNumber = number
    var i = 1
    while (iteration > i) {
        //yield()
        resultNumber *= BigInteger.TWO
        i++
    }
    return "Число $number умноженное на 2 $iteration раз = $resultNumber"
}
