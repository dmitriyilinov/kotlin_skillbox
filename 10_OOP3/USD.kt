class USD : CurrencyConverter {
    override val currencyCode = "USD"
    override fun convertRub(sum: Double) = sum / 69.0202
}