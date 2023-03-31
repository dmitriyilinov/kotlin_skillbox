class Bird(name: String, maxAge: Int, energy: Int, weight: Int) : Animal(name, maxAge, energy, weight) {

    override fun moving() {
        if ((energy > 5) && (weight > 1) && (!isTooOld())) {
            super.moving()
            println("flying")
        } else super.moving()
    }

    override fun procreation(): Bird {
        val child = Bird(name, maxAge, (1..10).random(), (1..5).random())
        child.currentAge = 0
        println(
            "Was born ${child.name}." +
                    "Current age - ${child.currentAge}, " +
                    "maxAge  - ${child.maxAge}," +
                    " currentEnergy - ${child.energy}," +
                    " currentWeight - ${child.weight}"
        )
        return child
    }
}