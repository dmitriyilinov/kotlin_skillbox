class EUR : CurrencyConverter {
    override val currencyCode = "EUR"
    override fun convertRub(sum: Double) = sum / 74.1361
}