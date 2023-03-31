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


//    LoadHelper.load()
//    println("Program start ${Thread.currentThread().name}")
//    magicNumber(object : CallBack {
//        override fun onSuccess(value: BigInteger) {
//            println("First number - $value")
//        }
//
//        override fun onFailure(error: Throwable) {
//            println("Error occurred - ${error.message}")
//        }
//    })
//    magicNumber(object : CallBack {
//        override fun onSuccess(value: BigInteger) {
//            println("Second number - $value")
//        }
//
//        override fun onFailure(error: Throwable) {
//            println("Error occurred - ${error.message}")
//        }
//    })
//fun main() = runBlocking {
//    launch {
//        printWorld()
//    }
//    launch {
//        printDots()
//    }
//
//    println("Hello ")
//}
val exeptionHandler = CoroutineExceptionHandler { _, throwable ->
    println("error from handler ${throwable.message}")
}

//val parentJob = Job()
val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default + exeptionHandler)
suspend fun main() {
//    scope.launch {
//        println("Start runBlocking")
//        suspendMagicNumber()
//        println("Finish runBlocking")
//    }
//    println("Start...")
    cancelledCoroutines()
    (scope.coroutineContext.job as? CompletableJob)?.let {
        it.complete()
        it.join()
    } ?: throw IllegalStateException()

}

fun cancelledCoroutines() {
    scope.launch {
        delay(3000)
        error("test exception")
    }
    scope.launch {
        var i = 0
        while (true) {
            delay(200)
            println(i++)
        }
    }
}

suspend fun suspendMagicNumber() {
    suspendCoroutine<BigInteger> { continuation ->
        magicNumber(object : CallBack {
            override fun onSuccess(value: BigInteger) {
                println("number $value")
                continuation.resume(value)
            }

            override fun onFailure(error: Throwable) {
                println("error ${error.message}")
                continuation.resumeWithException(error)
            }
        })


    }
}

suspend fun printWorld() {
    delay(2000)
    print("world!")
}

fun printDots() {
    for (i in 1..500) {
        //delay(10)
        if (i % 130 == 0) println(".$i")
        else print(".$i")
    }
}

fun magicNumber(callback: CallBack) {
    thread {
        println("Calculation start: ${Thread.currentThread().name}")
        val number: BigInteger
        val time = measureTimeMillis { number = BigInteger.probablePrime(4000, Random()) }
        if (time > 2000) callback.onFailure(Throwable("Calculation was too long"))
        else callback.onSuccess(number)
    }
}

interface CallBack {
    fun onSuccess(value: BigInteger)
    fun onFailure(error: Throwable)
}

object LoadHelper {
    private var progress: Int = 0
    private fun startLoading() {
        thread {
            while (progress < 100) {
                progress++
                Thread.sleep(500)
            }
        }
    }

    private fun updateProgressBar() {
        thread {
            while (progress < 100) {
                println(getProgressString())
                Thread.sleep(500)
            }
        }
    }

    private fun getProgressString(): String {
        val str = StringBuilder()
        for (i in 0..100) {
            if (i < progress) str.append("#") else str.append(".")
        }
        str.append(" $progress %")
        return str.toString()
    }

    fun load() {
        startLoading()
        updateProgressBar()
    }

}
