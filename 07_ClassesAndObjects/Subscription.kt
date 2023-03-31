class Subscription(val variant: Int) {
    private val mapSubscription = mapOf(1 to "NTV+", 2 to "FOOTBULL+", 3 to "CHILD+")
    val listSubscription = mutableListOf<String>(mapSubscription.getValue(variant))
}