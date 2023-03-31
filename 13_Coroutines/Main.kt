import kotlinx.coroutines.*
import java.lang.IllegalStateException
import java.math.BigInteger
import java.util.*
import javax.security.auth.callback.Callback
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

suspend fun main() {
    runBlocking {
        val job1 = async {
//определение случайным образом порядкового номера числа из ряда в диапазоне 1..10000
            val number = (1..10000).random()
            println("...Расчет 1 запущен на потоке ${Thread.currentThread().name} $number числа из ряда")
            try {
//отмена корутины при превышении 100 мс (п.7 задания)
                withTimeout(100) {
                    Fibonacci.take(number)
                }
            } catch (error: TimeoutCancellationException) {
                println("...Расчет 1 отменен для $number числа из ряда: $error")
            } catch (error: TooHighNumber) {
                println("...Расчет 1 отменен для $number числа из ряда: $error")
            }
        }

        val job2 = async {
            val number = (1..10000).random()
            println("...Расчет 2 запущен на потоке ${Thread.currentThread().name} $number числа из ряда")
            try {
                withTimeout(100) {
                    Fibonacci.take(number)
                }
            } catch (error: TimeoutCancellationException) {
                println("...Расчет 2 отменен для $number числа из ряда: $error")
            } catch (error: TooHighNumber) {
                println("...Расчет 2 отменен для $number числа из ряда: $error")
            }
        }

        val job3 = async {
            val number = (1..10000).random()
            println("...Расчет 3 запущен на потоке ${Thread.currentThread().name} $number числа из ряда")
            try {
                withTimeout(100) {
                    Fibonacci.take(number)
                }
            } catch (error: TimeoutCancellationException) {
                println("...Расчет 3 отменен для $number числа из ряда: $error")
            } catch (error: TooHighNumber) {
                println("...Расчет 3 отменен для $number числа из ряда: $error")
            }
        }
        val job4 = async {
            while (job1.isActive || job2.isActive || job3.isActive) {
                printDots()
            }
        }
        val result1 = job1.await()
        val result2 = job2.await()
        val result3 = job3.await()
//вывод результата
        if (result1 is BigInteger) println("1 - $result1") else println("1 - 0")
        if (result2 is BigInteger) println("2 - $result2") else println("2 - 0")
        if (result3 is BigInteger) println("3 - $result3") else println("3 - 0")
    }
}

object Fibonacci {
    suspend fun take(n: Int): BigInteger {
//если порядковый номер числа из рада выше 5000, то отмена корутины (п.5 задания)
        if (n > 5000) {
            throw TooHighNumber()
            currentCoroutineContext().job.cancelAndJoin()
        }
        var prevNumber: BigInteger = 1.toBigInteger()
        var prevPrevNumber: BigInteger = 1.toBigInteger()
        var currentNumber: BigInteger = 0.toBigInteger()
        if (n == 1 || n == 2) return 1.toBigInteger()
        for (i in 3..n) {
            currentNumber = prevNumber + prevPrevNumber
            prevPrevNumber = prevNumber
            prevNumber = currentNumber
            yield()
        }
        return currentNumber
    }
}

suspend fun printDots() {
    println("...")
    delay(3)
}

class TooHighNumber : Throwable(message = "Порядковый номер числа выше 5000")
