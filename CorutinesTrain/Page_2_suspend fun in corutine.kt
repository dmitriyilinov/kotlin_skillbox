package corutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*Давайте теперь разбираться как написать корутину. Прежде всего корутина запускается и определяется построителем
(билдером) корутины launch или async - если говорить совсем по-простому, то это и есть корутина. Т.е. корутина - это
какое-то вычисление (блок кода), которое помещается в билдер launch или await. Она выполняет свои вычисления
параллельно основному потоку и может быть приостановлена. Сама корутина может быть запущена только внутри определенной
области runBlocking или coroutineScope (является suspend, поэтому main должен быть с тем же модификатором).

Возьмём наш таймер из прошлого примера и поместим его в suspend функцию countdown
 */


suspend fun main() {

    // область выполнения корутины
    coroutineScope {

        // корутина
        launch {
            // запускаем в ней suspend функцию countdown
            countdown()
        }
    }
}

// функция обратного отсчёта
suspend fun countdown() {
    for (counter in 10 downTo 0) {
        println(counter)
        delay(1000)
    }
}