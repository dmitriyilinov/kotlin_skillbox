package corutines

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/* Рассмотрим пример по сложнее. Нужно понимать следующее, что если мы отменяем корутину в которой выполняются другие
корутины, они тоже будут отменены. Поэтому иногда легче сгруппировать корутины в одну и отменить ее в случае надобности.

Создадим лист победителей. В coroutineScope запустим сто корутин с рандомной задержкой выполнения. Как только одна
(или несколько в случае одинакового времени) корутин отправят сообщение в лист победителей. Другая корутина закроет
родительскую с оставшимися корутинами. Так же в этом примере можно обратить внимания на сколько быстро работают корутины,
и что открыть их можно огромное количество.*/

suspend fun main() {

    val listOfWinners = mutableListOf<String>()

    coroutineScope {

        // родительская корутина внутри нее создаем ещё сто
        val gamblers = launch {

            // создаем лист игроков
            val gamblersList = (1..100).toList()

            // для каждого игрока открываем свою корутину
            gamblersList.forEach {
                launch {
                    val timeRandom = (3000..10000).random().toLong()
                    delay(timeRandom)
                    // как только время закончится, корутина добавит сообщение о себе в лист listOfWinners
                    listOfWinners.add("Корутина $it победила (задержка составила $timeRandom). Оставшиеся закрыты")
                }
            }
        }

        /* корутина наблюдатель, если в listOfWinners добавлен победитель (или победители), перестает печатать точки
        и закрывает корутину gamblers*/
        val dotsJob = launch {
            var dot = 1
            while (listOfWinners.isEmpty()) {
                if (dot % 100 == 0) {
                    println(".")
                } else {
                    print(".")
                }
                dot++
                delay(50)

            }
            println()
            gamblers.cancelAndJoin()
        }

        // дожидаемся завершения работы корутины dotsJob и выводим сообщение о победителе
        dotsJob.join()
        listOfWinners.forEach { println(it) }
    }

}