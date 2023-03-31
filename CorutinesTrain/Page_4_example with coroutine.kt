package corutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/* А теперь выполним то же код, но расчёт числа поместим в корутину. Корутина будет производить вычисление параллельно
основному потоку и не будет стопорить оставшийся код.*/

suspend fun main() {

    coroutineScope {

        val text = "7рфЯ7#ёулрмрсузг7зПапгбм]йжцъ]имосржлщ"
        println(text)

        // запускаем функцию расшифровки первой части
        val fistPart = decryptFist(text)

        // вычисляем большое число в корутине. Корутина производит свои вычисления параллельно основному коду.
        launch {
            calculateNumber()
        }

        /* Обращаем внимание, что сразу начинается расшифровка второй части сообщения, не дожидаясь результата выполнения
            корутины. */
        val secondPart = decryptSecond(text)
        println("Сообщение расшифровано: ${fistPart + secondPart}")
    }
}