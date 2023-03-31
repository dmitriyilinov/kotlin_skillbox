import kotlin.random.Random

open class Animal(val name: String, val maxAge: Int, var energy: Int, var weight: Int) {
    var currentAge: Int = (1..maxAge).random()

    protected fun isTooOld() = currentAge >= maxAge

    fun sleep() {
        if (!isTooOld()) {
            currentAge += 1
            energy += 5
            println("$name sleeping. CurrentAge - $currentAge, currentEnergy - $energy, currentWeight - $weight")
        } else println("$name don't sleeping")
    }

    fun eat() {
        if (!isTooOld()) {
            energy += 3
            weight += 1
            currentAge += tryIncrementAge()
            println("$name eats. CurrentAge - $currentAge, currentEnergy - $energy, currentWeight - $weight")
        } else println("$name don't eats")
    }

    open fun moving() {
        if ((energy > 5) && (weight > 1) && (!isTooOld())) {
            energy -= 5
            weight -= 1
            currentAge += tryIncrementAge()
            println("$name moving. CurrentAge - $currentAge, currentEnergy - $energy, currentWeight - $weight")
        } else println("$name don't moving")
    }

    open fun procreation(): Animal {
        val child = Animal(name, maxAge, (1..10).random(),(1..5).random())
        child.currentAge = 0
        println("Was born ${child.name}. Current age - ${child.currentAge}, maxAge  - ${child.maxAge}, currentEnergy - ${child.energy}, currentWeight - ${child.weight}")
        return child
    }

    companion object {
        fun tryIncrementAge(): Int {
            if (Random.nextBoolean()) return 1 else return 0
        }
    }
}