fun main() {
    println("Enter count iterations (count >= 1)")
    var count = readLine()?.toIntOrNull() ?: return
    while (count < 1) {
        println("Enter count iterations (count >= 1)")
        count = readLine()?.toIntOrNull() ?: return
    }
    val f = NatureReserve()
    println("===== initial count - ${f.fauna.count()}")
    for (i in 1..count) {
        f.cycleLife()
        f.funeral()
    }
    println("===== count of born animals - ${f.born}")
    println("===== count of dead animals - ${f.dead}")
    println("===== final count - ${f.fauna.count()}")
}