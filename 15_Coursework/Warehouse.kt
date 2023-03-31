class Warehouse {
    val storage = mutableMapOf<Product, Int>()
    @Synchronized
    fun getProduct(typeProduct: TypeProduct, count: Int): MutableMap<Product, Int> {
        val forUnload = mutableMapOf<Product, Int>()
        var currentCount = count
        if (storage.entries.filter { it.key.type == typeProduct }.sumOf { it.value } >= 0) {
            storage.entries.filter { it.key.type == typeProduct }.forEach {
                if (it.value > currentCount) {
                    forUnload += it.key to currentCount
                    storage += it.key to (it.value - currentCount)
                    currentCount = 0
                } else if (it.value == currentCount) {
                    forUnload += it.key to currentCount
                    storage.remove(it.key)
                    currentCount = 0
                } else if (it.value < currentCount) {
                    forUnload += it.key to it.value
                    storage.remove(it.key)
                    currentCount -= it.value
                }
            }
        }
        return forUnload
    }
    @Synchronized
    fun putProduct(product:MutableMap<Product, Int>) {
        for (entry in product.entries) {
            if (storage[entry.key] == null) {
                storage[entry.key] = entry.value
            } else {
                val value = storage[entry.key]
                storage[entry.key] = entry.value + value!!
            }
        }
    }
}