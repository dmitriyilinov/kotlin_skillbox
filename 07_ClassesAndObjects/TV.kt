class TV(val brand: String, val model: String, val diagonal: Int) {
    var included: Boolean = false
        private set
    private var channelList: MutableList<String> =
        Channels.getRandomChannels(BrandTV.getMaxCountChannel(brand)).toMutableList()
    private var currentChannel = channelList[0]
    private var volume = 10
    var countChannel = BrandTV.getMaxCountChannel(brand)
        private set

    fun applySubscription(variant: Int) {
        channelList.addAll(Subscription(variant).listSubscription)
        countChannel++
    }

    fun printAllListChannel() {
        channelList.forEachIndexed { index, item ->
            Thread.sleep(500)
            println("№${index + 1} - $item")
        }
    }

    fun on() {
        included = true
        println("TV on. Current volume $volume. Current channel $currentChannel")
    }

    fun off() {
        included = false
        println("TV off")
    }

    fun upVolume(volume: Int) {
        this.volume = minOf(this.volume + volume, maxVolume)
        println("Volume ++. Current volume ${this.volume}")
    }

    fun downVolume(volume: Int) {
        this.volume = maxOf(this.volume - volume, 0)
        println("Volume --. Current volume ${this.volume}")
    }

    fun selectChannel(number: Int) {
        if (!included) on()
        currentChannel = channelList[number - 1]
        printCurrentChannel()
    }

    private fun printCurrentChannel() {
        println("Current channel №${channelList.indexOf(currentChannel) + 1} - $currentChannel")
    }

    fun upChannel() {
        if (!included) on()
        if (currentChannel == channelList.last()) {
            currentChannel = channelList.first()
        } else {
            currentChannel = channelList[channelList.indexOf(currentChannel) + 1]
        }
        printCurrentChannel()
    }

    fun downChannel() {
        if (!included) on()
        if (currentChannel == channelList.first()) {
            currentChannel = channelList.last()
        } else {
            currentChannel = channelList[channelList.indexOf(currentChannel) - 1]
        }
        printCurrentChannel()
    }

    companion object {
        const val maxVolume = 30
    }
}