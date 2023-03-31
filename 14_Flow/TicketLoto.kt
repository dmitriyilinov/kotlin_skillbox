class TicketLoto {
    var ticket = arrayOf<Array<Int>>()

    fun initTicket() {
        for (i in 0..2) {
            var line = arrayOf<Int>()
            val placeLine = arrayOf<Int>(1, 0, 1, 0, 1, 0, 1, 0, 1)
            placeLine.shuffle()
            var number = 0
            for (j in 0..8) {
                if (placeLine[j] == 1) {
                    do {
                        when (j) {
                            0 -> number = (1..9).random()
                            1 -> number = (10..19).random()
                            2 -> number = (20..29).random()
                            3 -> number = (30..39).random()
                            4 -> number = (40..49).random()
                            5 -> number = (50..59).random()
                            6 -> number = (60..69).random()
                            7 -> number = (70..79).random()
                            8 -> number = (80..90).random()
                        }
                    } while (isContains(number))
                    line += number
                } else line += 0
            }
            ticket += line
        }
    }

    private fun isContains(n: Int): Boolean {
        var result = false
        for (line in ticket) {
            result = result || line.contains(n)
        }
        return result
    }

    fun receiveKeg(n: Int) {
        if (isContains(n)) {
            for (i in (0..2)) {
                for (j in (0..8)) {
                    if (ticket[i][j] == n) ticket[i][j] = 0
                }
            }
        }
    }
}