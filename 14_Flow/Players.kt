class Players(n: Int) {
    val tickets = mutableListOf<TicketLoto>()

    init {
        for (i in 1..n) {
            val ticket = TicketLoto()
            ticket.initTicket()
            tickets.add(ticket)
        }
    }

    fun checkVictory(): Boolean {
        var result = false
        tickets.forEach {
            for (line in it.ticket) {
                if (line.sum() == 0) result = true
            }
        }
        return result
    }
}