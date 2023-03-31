class NatureReserve() {
    val fauna = mutableListOf(
        Bird("Bird1", 10, 10, 10),
        Bird("Bird2", 10, 10, 10),
        Bird("Bird3", 10, 10, 10),
        Bird("Bird4", 10, 10, 10),
        Bird("Bird5", 10, 10, 10),
        Fish("Fish1", 15, 15, 15),
        Fish("Fish2", 15, 15, 15),
        Fish("Fish3", 15, 15, 15),
        Dog("Dog1", 20, 20, 20),
        Dog("Dog2", 20, 20, 20),
        Animal("Animal1", 25, 25, 25),
        Animal("Animal2", 25, 25, 25),
        Animal("Animal3", 25, 25, 25),
        Animal("Animal4", 25, 25, 25)
    )

    var born: Int = 0
    var dead: Int = 0

    fun cycleLife() {
        for (i in fauna.indices) {
            when ((1..4).random()) {
                1 -> fauna[i].moving()
                2 -> fauna[i].eat()
                3 -> fauna[i].sleep()
                4 -> {
                    fauna.add(fauna[i].procreation())
                    born += 1
                }
            }
        }

    }

    fun funeral() {
        dead += fauna.count()
        fauna.removeAll { it.currentAge >= it.maxAge }
        dead -= fauna.count()
    }
}