object Channels {
    private val allChannels =
        listOf("1tv", "Russia1", "Match", "NTV", "5 channel", "Culture", "Carousel", "OTR", "TV centre", "REN TV")
    val maxCountChannels = 10

    fun getRandomChannels(count: Int): List<String> {
        return if (count > maxCountChannels) {
            allChannels.shuffled().take(maxCountChannels)
        } else {
            allChannels.shuffled().take(count)
        }
    }
}