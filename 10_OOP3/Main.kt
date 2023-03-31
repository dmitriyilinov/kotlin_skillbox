fun main() {
    var sum: Double
    var cod = ""
    println("Конвертер валют: вам необходимо ввести сумму в рублях, затем символьный код валюты (Q - выход)")
    while (cod != "Q") {
        print("Введите сумму в рублях: ")
        sum = readLine()?.toDoubleOrNull() ?: return
        print("Введите символьный код валюты: ")
        cod = readLine().toString()
        if (cod != "Q") println(
            "$sum рублей = ${
                String.format(
                    "%.2f",
                    Converters.get(cod).convertRub(sum)
                )
            } %$cod%"
        )
    }
}

object Converters {
    private val currency = listOf(USD(), EUR(), CNY())
    fun get(currencyCode: String): CurrencyConverter {
        val anonCurrency = object : CurrencyConverter {
            override val currencyCode = currencyCode
            override fun convertRub(sum: Double) = 0.00
        }
        return currency.find { it.currencyCode == currencyCode } ?: anonCurrency
    }
}