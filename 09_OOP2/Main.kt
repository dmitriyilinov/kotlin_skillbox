fun main() {
    val card1 = MirDebitCard()
    val card2 = MirCreditCard()
    var variantStep: String
    var sum: Int
    println(
        """Банк выдал вам две карты.
            |МИР Дебетовая (1) и МИР Кредитная с лимитом 10 000 (2).
            |По какой карте вы хотите сейчас совершать операции? (1 или 2)""".trimMargin()
    )
    var variantCard = readLine()?.toIntOrNull() ?: return
    while (variantCard !in 1..2) {
        println("По какой карте вы хотите сейчас совершать операции? (1 или 2)")
        variantCard = readLine()?.toIntOrNull() ?: return
    }
    when (variantCard) {
        1 -> {
            println("МИР Дебетовая")
            printAvialableCash(card1.getAvailableCash(), variantCard)
            println(
                """Выберите вариант действия с картой:
            |+ : пополнение на сумму
            |- : оплата на сумму
            |* : узнать баланс
            |/ : получить информацию о всех доступных средствах
            |Q : выход""".trimMargin()
            )
            do {
                variantStep = readLine().toString()
                when (variantStep) {
                    "+" -> {
                        print("Введите сумму: ")
                        sum = readLine()?.toIntOrNull() ?: return
                        card1.replenish(sum)
                        println("Пополнение карты на сумму $sum - выполнено!")
                        println("-----")
                    }

                    "-" -> {
                        print("Введите сумму: ")
                        sum = readLine()?.toIntOrNull() ?: return
                        if (card1.pay(sum)) {
                            println("Платеж с карты на сумму $sum - выполнен!")
                            println("-----")
                        } else {
                            println("Платеж с карты на сумму $sum - отклонен!")
                            println("На карте не достаточно средств!")
                            println("-----")
                        }
                    }

                    "*" -> {
                        printBalance(card1.getInfBalance())
                    }

                    "/" -> {
                        printAvialableCash(card1.getAvailableCash(), variantCard)
                    }
                }
            } while (variantStep != "Q")
        }

        2 -> {
            println("МИР кредитная")
            printAvialableCash(card2.getAvailableCash(), variantCard)
            println(
                """Выберите вариант действия с картой:
            |+ : пополнение на сумму
            |- : оплата на сумму
            |* : узнать баланс
            |/ : получить информацию о всех доступных средствах
            |Q : выход""".trimMargin()
            )
            do {
                variantStep = readLine().toString()
                when (variantStep) {
                    "+" -> {
                        print("Введите сумму: ")
                        sum = readLine()?.toIntOrNull() ?: return
                        card2.replenish(sum)
                        println("Пополнение карты на сумму $sum - выполнено!")
                        println("-----")
                    }

                    "-" -> {
                        print("Введите сумму: ")
                        sum = readLine()?.toIntOrNull() ?: return
                        if (card2.pay(sum)) {
                            println("Платеж с карты на сумму $sum - выполнен!")
                            println("-----")
                        } else {
                            println("Платеж с карты на сумму $sum - отклонен!")
                            println("На карте не достаточно средств!")
                            println("-----")
                        }
                    }

                    "*" -> {
                        printBalance(card2.getInfBalance())
                    }

                    "/" -> {
                        printAvialableCash(card2.getAvailableCash(), variantCard)
                    }
                }
            } while (variantStep != "Q")
        }
    }
}

fun printAvialableCash(cash: List<Int>, variant: Int) {
    if (variant == 1) {
        println("Баланс карты - ${cash[4]}, в т.ч.:")
        println("Кэшбэк - ${cash[0]}")
        println("Собственные средства - ${cash[3]}")
        println("-----")
    } else {
        println("Баланс карты - ${cash[4]}, в т.ч.:")
        println("Кэшбэк - ${cash[0]}")
        println("Кредитный лимит - ${cash[1]}")
        println("Кредитные средства - ${cash[2]}")
        println("Собственные средства - ${cash[3]}")
        println("-----")
    }
}

fun printBalance(balance: Int) {
    println("Баланс карты - $balance")
    println("-----")
}