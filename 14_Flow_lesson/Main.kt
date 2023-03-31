import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.random.Random

suspend fun main() {
    //val scope = CoroutineScope(Job() + Dispatchers.Default)
    val scope = coroutineScope {

        val job1 = launch {
            flow.collect {
                println("First -$it - ${Thread.currentThread()}")
                delay(100)
            }
        }
        val job2 = launch {
            flow.collect {
                println("Second -$it - ${Thread.currentThread()}")
                delay(100)
            }
        }
        val job3 = launch {
            flow.collect {
                println("Third -$it - ${Thread.currentThread()}")
                if (it == 5) {
                    job1.cancel()
                    job2.cancel()
                    cancel()
                }
                delay(100)
            }
        }


    }
    println("fff")


//    runBlocking {
//        launch {
//            println("Start collecting...")
//            var counter = 0
//            Generator.flow.collect {
//            //flow().collect {
//                println("Collected value - $it")
//                delay(1000)
//                counter++
//                if (counter >=10) cancel("Finish")
//
//            }
//        }
//        println("Start working...")
//    }

//    runBlocking {
//        launch {
//            intermediateFlow.collect {
//                println(it)
//            }
//        }
//    }

//    runBlocking {
//        launch {
//            distinctFlow
//                .distinctUntilChanged()
//                .collect {
//                println(it)
//            }
//        }
//    }
//    val nums = (1..3).asFlow().onEach { delay(1000) }
//    val strs = flowOf("one", "two", "three").onEach { delay(2000) }
//    runBlocking {
//        launch {
//            nums
//                //.zip(strs) { number, string -> "$number - $string" }
//                .combine(strs) { number, string -> "$number - $string" }
//                .collect { println(it) }
//        }
//    }
//    val flow = flow {
//        repeat(5) {
//            delay(1000)
//            val number = Random.nextInt(1, 101)
//            if (Random.nextBoolean()) error("Error occurred for $number")
//            emit(number)
//        }
//    }
//    runBlocking {
//        flow.catch {throw it }
//            .onEach { println(it) }
//            .map { if (it > 50) error("too much $it") }
//            .catch { println("mapping error $it") }
//            .launchIn(this)
//    }
//    runBlocking {
//        val job = launch {
//            //var count = 0
//            GeneratorShared.sharedFlow.takeWhile { it != 100 }.collect {
//                println(" 1 - $it")
//                //  count++
//                // if (count > 5) cancel()
//
//            }
////        delay(5000)
////        job.cancel()
//        }
//    }
//    val numbers = Numbers()
//    runBlocking {
//        launch {
//            delay(2000)
//            withTimeout(10000) {
//                numbers.sharedFlow.collect {
//                    println(it)
//                }
//            }
//        }
//        launch {
//            delay(4000)
//            withTimeout(8000) {
//                numbers.sharedFlow.collect {
//                    println("second collector $it")
//                }
//            }
//        }
//    }
}

val flow = (0..10).shuffled().asFlow()

class Numbers {
    private val scope = CoroutineScope(Job() + Dispatchers.Default)
    private val _sharedFlow = MutableSharedFlow<Int>(replay = 4)
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        scope.launch {
            for (i in 0..100) {
                _sharedFlow.emit(i)
                delay(500)
            }
        }
    }
}


object GeneratorShared {
    private val scope = CoroutineScope(Job() + Dispatchers.Default)
    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        scope.launch {
            repeat(10) {
                _sharedFlow.emit(Random.nextInt(90, 101))
                delay(500)
            }
        }
    }

}

val distinctFlow = flow {
    repeat(10) {
        emit(Random.nextBoolean())
        delay(500)
    }
}

val intermediateFlow = (0..10)
    .asFlow()
    //.map { it * it }
    .square()
    .filter { it % 2 == 0 }
    .take(3)

fun Flow<Int>.square(): Flow<Int> = transform { value ->
    return@transform emit(value * value)
}

object Generator {
    val flow = flow {
        while (currentCoroutineContext().isActive) {
            emit(Random.nextInt())
            kotlinx.coroutines.delay(10000)
        }
    }
}

//fun flow(): Flow<Int> {
//    return (0..20).asFlow()
//}