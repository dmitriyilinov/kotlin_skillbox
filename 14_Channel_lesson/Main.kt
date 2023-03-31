import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import java.rmi.server.LogStream.log
import java.time.Duration
import kotlin.random.Random

suspend fun main() = coroutineScope {

//    val channel = Channel<Int>()
//    launch {
//        for (n in 1..5) {
//            // отправляем данные через канал
//            channel.send(n)
//        }
//    }
//
//    // получаем данные из канала
//    repeat(5) {
//        val number = channel.receive()
//        println(number)
//    }
//    println("End")


//    val channel = Channel<String>()
//    launch {
//        val users = listOf("Tom", "Bob", "Sam")
//        for (user in users) {
//            println("Sending $user")
//            channel.send(user)
//            delay(1000)
//        }
//    }
//
//    repeat(3) {
//        val user = channel.receive()
//        println("Received: $user")
//    }
//    println("End")

//    val channel = Channel<String>()
//    launch {
//        val users = listOf("Tom", "Bob", "Sam")
//        for (user in users) {
//            channel.send(user)  // Отправляем данные в канал
//        }
//        channel.close()  // Закрытие канала
//    }

//    for(user in channel) {  // Получаем данные из канала
//        println(user)
//    }
//    println("End")
//    launch {
//        val users1 = getUsers()
//        users1.consumeEach { user -> println("1 $user") }
//        println("End1")
//    }
//    //println("End1")
//    launch {
//        val users2 = getUsers()
//        users2.consumeEach { user -> println("2 $user") }
//        println("End2")
//    }
//    println("End")


    val pizzaOrders = producePizzaOrders()
    repeat(3) {
        pizzaOrderProcessor(it + 1, pizzaOrders)
        //delay(1000)
    }

    delay(10000)
    pizzaOrders.cancel()

//    val aggregate = Channel<String>()
//    launch { fetchYoutubeVideos(aggregate) }
//    launch { fetchTweets(aggregate) }
//
//    repeat(4) {
//        println(aggregate.receive())
//    }
//
//    coroutineContext.cancelChildren()

//    val orders = produceOrders(3)
//
//    val readyOrders = topping(baking(orders))
//
//    for (order in readyOrders) {
//        println("Serving ${order}")
//    }
//
//    delay(3000)
//    coroutineContext.cancelChildren()

//    val tickerChannel = ticker(Duration.ofSeconds(5).toMillis())
//
//    repeat(3) {
//        tickerChannel.receive()
//        println(stockPrice("TESLA"))
//    }
//
//    delay(Duration.ofSeconds(11).toMillis())
//    tickerChannel.cancel()


}

fun CoroutineScope.getUsers(): ReceiveChannel<String> = produce {
    val users = listOf("Tom", "Bob", "Sam")
    for (user in users) {
        send(user)
        delay(300)
    }
}


fun CoroutineScope.producePizzaOrders(): ReceiveChannel<String> = produce {
    var x = 1
    while (true) {
        send("Pizza Order No. ${x++}")
        //delay(Random.nextLong(100,2000))
    }
}

fun CoroutineScope.pizzaOrderProcessor(id: Int, orders: ReceiveChannel<String>) = launch {
    for (order in orders) {
        val x = Random.nextLong(100,3000)
        delay(x)
        println("Processor #$id is processing $order x-$x")
    }
}

suspend fun fetchYoutubeVideos(channel: SendChannel<String>) {
    val videos = listOf("cat video", "food video")
    for (video in videos) {
        delay(100)
        channel.send(video)
    }
}

suspend fun fetchTweets(channel: SendChannel<String>) {
    val tweets = listOf("tweet: Earth is round", "tweet: Coroutines and channels are cool")
    for (tweet in tweets) {
        delay(100)
        channel.send(tweet)
    }
}

fun CoroutineScope.baking(orders: ReceiveChannel<Int>) = produce {
    for (order in orders) {
        delay(200)
        println("Baking ${order}")
        send(order)
    }
}

fun CoroutineScope.topping(orders: ReceiveChannel<Int>) = produce {
    for (order in orders) {
        delay(50)
        println("Topping ${order}")
        send(order)
    }
}

fun CoroutineScope.produceOrders(count: Int) = produce {
    repeat(count) {
        delay(50)
        send(it + 1)
    }
}

fun stockPrice(stock: String): Double {
    println("Fetching stock price of $stock")
    return Random.nextDouble(2.0, 3.0)
}
