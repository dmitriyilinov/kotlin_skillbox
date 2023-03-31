import kotlinx.coroutines.*
import java.lang.IllegalStateException

suspend fun main(){
    val job = scope.launch {
        var i=0
        while (true) {
            println(i++)
            //delay(500)
            Thread.sleep(500)
            yield()
        }
    }
    scope.launch {
        delay(3000)
        println("cancel job")
        job.cancel()
    }
    (scope.coroutineContext.job as? CompletableJob)?.let {
        it.complete()
        it.join()
    } ?: throw IllegalStateException()

}