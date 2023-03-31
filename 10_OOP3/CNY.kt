class CNY : CurrencyConverter {
    override val currencyCode = "CNY"
    override fun convertRub(sum: Double) = sum / 10.1396
}