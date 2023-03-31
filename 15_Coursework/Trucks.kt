import kotlinx.coroutines.delay
import kotlin.random.Random

class Trucks {
    var typeTrucks = TypeTrucks.GAZ
    var storage = mutableMapOf<Product, Int>()

    suspend fun loadTruck(productForLoad: MutableMap<Product, Int>) {
        productForLoad.entries.forEach {
            if (storage[it.key] == null) {
                storage[it.key] = it.value
            } else {
                val value = storage[it.key]
                storage[it.key] = it.value + value!!
            }
            delay((it.value * it.key.duration).toLong())
        }
    }

    fun createLoadTruck() {
        when ((1..2).random()) {
            (1) -> {
                typeTrucks = TypeTrucks.GAZ
            }

            (2) -> {
                typeTrucks = TypeTrucks.GAZON
            }
        }
    }

    suspend fun unloadTruck(): MutableMap<Product, Int> {
        val productForUnload = mutableMapOf<Product, Int>()
        for (entry in storage.entries) {
            productForUnload += entry.key to entry.value
            delay((entry.value * entry.key.duration).toLong())
        }
        storage.clear()
        return productForUnload
    }

    fun createUnloadTruck() {
        when ((1..3).random()) {
            (1) -> {
                typeTrucks = TypeTrucks.GAZ
            }

            (2) -> {
                typeTrucks = TypeTrucks.GAZON
            }

            (3) -> {
                typeTrucks = TypeTrucks.KAMAZ
            }
        }
        when ((1..2).random()) {
            (1) -> {
                val countForLoad = (0..typeTrucks.capacity).random()
                val countProductType = Product.values().count { it.type == TypeProduct.FOOD }
                Product.values().forEach {
                    if (it.type == TypeProduct.FOOD) {
                        storage[it] = countForLoad / countProductType
                    }
                }
            }

            (2) -> {
                var countLoad = 0
                val countProductType = Product.values().count { it.type != TypeProduct.FOOD }
                var countForLoad = 0
                Product.values().forEach {
                    if (it.type != TypeProduct.FOOD && Random.nextBoolean()) {
                        countForLoad = (0..(typeTrucks.capacity - countLoad)).random()
                        storage[it] = countForLoad / countProductType
                        countLoad += countForLoad / countProductType
                    }
                }
            }
        }
    }
}

enum class TypeTrucks(val capacity: Int, val type: TypeCapacity) {
    GAZ(2500, TypeCapacity.LOW),
    GAZON(10000, TypeCapacity.MEDIUM),
    KAMAZ(20000, TypeCapacity.HIGH)
}

enum class TypeCapacity {
    LOW, MEDIUM, HIGH
}