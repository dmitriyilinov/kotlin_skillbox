package corutines

import kotlinx.coroutines.*

/* Обращение к job мы так же можем выполнить метод start(). В принимаемых параметрах для любого билдера корутины
можно задать отложенный вызов таким образом например launch(CoroutineStart.LAZY). Это означает, что корутина создана,
но она не будет запущена автоматически, а только когда ее вызовут через метод start.
Давайте рассмотрим на примере.
 */

suspend fun main() {

    coroutineScope {
        println("Пример 1")

        println("Пробуем запустить таймер")
        delay(1000)

        // создадим корутину с отложенным запуском, которая должна выполнять функцию таймер обратного отсчёта
        val job = launch(start = CoroutineStart.LAZY) {
            countdown()
        }

        println("Таймер закончил выполнение")
        delay(1000)
        println("Как мы видим таймер не был запущен")
        delay(1000)

        /* обратите внимания на следующую строчку кода, мы отложили выполнение корутины, но она создана. И если мы ее
        не закроем то программа застопорится. Завершение корутины выполним командой cansel(). Попробуйте закомментировать
        эту строчку кода и посмотрите что получится*/
        job.cancel()
    }


    coroutineScope {
        println("Пример 2")

        // создадим корутину с отложенным запуском, которая должна выполнять функцию таймер обратного отсчёта
        val job = launch(start = CoroutineStart.LAZY) {
            countdown()
        }

        println("Пробуем запустить таймер")
        // запускаем корутину с помощью команды start
        job.start()

        // дожидаемся выполнения корутины прежде чем выдать сообщение, что таймер закончил выполнение
        job.join()
        println("Таймер закончил выполнение")
    }
}