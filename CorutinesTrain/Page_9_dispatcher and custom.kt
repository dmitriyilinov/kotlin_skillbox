package corutines

import kotlinx.coroutines.*

/* Эта тема может показаться запутанной, но когда вы перейдете к модулю андроид, вы увидите это на практике.
Так что пока просто ознакомитесь, что бы знать об этом, а потом станет понятно зачем это всё нужно.
Все билдеры корутин, в качестве параметра принимают объект типа CoroutineContext,
который может использоваться для определения диспетчера создаваемой корутины. По умолчанию стоит Dispatcher.Default и
в большинстве случаев этого достаточно. Но например если вы выполняете сетевой запрос, то для этих целей устанавливается
другой Dispatcher а именно Dispatchers.IO

краткое описание диспетчеров:

Dispatchers.Default: применяется по умолчанию, если тип диспетчера не указан явным образом.
Этот тип использует общий пул разделяемых фоновых потоков и подходит для вычислений,
которые НЕ работают с операциями ввода-вывода (операциями с файлами, базами данных, сетью)
и которые требуют интенсивного потребления ресурсов центрального процессора.

Dispatchers.IO: использует общий пул потоков, создаваемых по мере необходимости,
и предназначен для выполнения операций ввода-вывода (например, операции с файлами или сетевыми запросами).

Dispatchers.Main: применяется в графических приложениях, например, в приложениях Android или JavaFX.

Dispatchers.Unconfined: корутина не закреплена четко за определенным потоком или пулом потоков.
Она запускается в текущем потоке до первой приостановки.
После возобновления работы корутина продолжает работу в одном из потоков, который сторого не фиксирован.
Разработчики языка Kotlin в обычной ситуации не рекомендуют использовать данный тип.


Следующая информация тоже не совсем может быть понятно, но она пригодится вам чуть ли не сразу в домашках по андроиду.
Вы можете объявлять свои Job и CoroutineScope в переменные, а потом передавать их в нужное место кода. Это может
увеличить их зону видимости для программы.*/


/* Это немного корявый пример, здесь собрано что попало. Просто прочтите его. Далее в андроиде отдельные блоки
этого примера могут вам помочь. Например - про зоны видимости и объявления Dispatchers. Так же шибком
не останавливаетесь на cancelAndJoin() далее я более менее подробно расскажу про это.*/

suspend fun main() {
    // создаем свой собственный Job
    val progress = Job()
    // создаем собственный CoroutineScope и помещаем в него нашу Job
    val scope = CoroutineScope(Dispatchers.Default + progress)

    // запускаем корутину с помощью нашего CoroutineScope
    scope.launch {

        /* внутри нашего CoroutineScope запускаем корутины
        которые будут бесконечно печать название потока на котором выполняются */
        launch(Dispatchers.IO) {
            while (true) {
                print("корутина Dispatchers.IO: ")
                threadCurrent()
            }
        }

        launch {
            while (true) {
                print("корутина Dispatchers.Default: ")
                threadCurrent()
            }
        }

        launch(Dispatchers.Unconfined) {
            while (true) {
                print("корутина Dispatchers.Unconfined: ")
                threadCurrent()
            }
        }

        // внутри своего же CoroutineScope завершаем собственную Job
        delay(5000)
        progress.cancelAndJoin()
        // это можно сделать и таким образом this.coroutineContext.job.cancelAndJoin()


    }.join() // это нужно, что бы программа дождалась выполнения кода в нашем CoroutineScope

    println("Программа завершена")
}


// функция вывода в консоль названия потока
suspend fun threadCurrent() {
    println("Корутина выполняется на потоке: ${Thread.currentThread().name}")
    delay(1500)
}

