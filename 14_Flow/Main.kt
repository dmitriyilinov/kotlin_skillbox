import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect

suspend fun main() {
    println("В данной игре принимают участие два игрока!")
    println("Введите количество карт у одного игрока")
    val countTicket = readLine()?.toIntOrNull() ?: return

    val player1 = Players(countTicket)
    val player2 = Players(countTicket)

    println("Карты игрока 1 до начала игры")
    Thread.sleep(1000)
    player1.tickets.forEach { printTickets(it) }
    println("===============================================")
    println("Карты игрока 2 до начала игры")
    Thread.sleep(1000)
    player2.tickets.forEach { printTickets(it) }
    println("===============================================")

    runBlocking {
        println("Игра началась, вот номера бочонков:")
        val job1 = launch {
            Presenter.flow.collect { numberKeg ->
                player1.tickets.forEach { it -> it.receiveKeg(numberKeg) }
                delay(100)
            }
        }
        val job2 = launch {
            Presenter.flow.collect { numberKeg ->
                player2.tickets.forEach { it -> it.receiveKeg(numberKeg) }
                delay(100)
            }
        }
        launch {
            var gameProgress = 0
            Presenter.flow.collect { numberKeg ->
                gameProgress++
                if (gameProgress % 30 == 0) println("$numberKeg ") else print("$numberKeg ")
                if (player1.checkVictory()) {
                    println("")
                    println("игра завершена на $gameProgress ходу, выиграл игрок 1")
                    println("===============================================")
                    job1.cancel()
                    job2.cancel()
                    cancel()
                }
                if (player2.checkVictory()) {
                    println("")
                    println("игра завершена на $gameProgress ходу, выиграл игрок 2")
                    println("===============================================")
                    job1.cancel()
                    job2.cancel()
                    cancel()
                }
                delay(100)
            }
        }
    }
    println("Карты игрока 1 после завершения игры")
    player1.tickets.forEach { numberTicket -> printTickets(numberTicket) }
    println("===============================================")
    println("Карты игрока 2 после завершения игры")
    player2.tickets.forEach { numberTicket -> printTickets(numberTicket) }
}

fun printTickets(ticket: TicketLoto) {
    println("+---------------------------------------------+")
    for (line in ticket.ticket) {
        print("| ")
        for (value in line) {
            if (value >= 10) print(" $value |") else
                if (value == 0) print("    |") else print("  $value |")
        }
        println()
        println("+---------------------------------------------+")
    }
}

object Presenter {
    val flow = (1..90).shuffled().asFlow()
}