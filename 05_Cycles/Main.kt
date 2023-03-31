fun main() {
    println("Enter n-number from Fibonacci series (0 < n < 47)")
// number must be positive and type Int support numbers before 2,147,483,647
    print("n=")
    var n = readLine()?.toIntOrNull() ?: return
    while (n <= 0 || n > 46) {
        println("Enter number 0 < n < 47")
        n = readLine()?.toIntOrNull() ?: return
    }
    println("Output all number before n-number from Fibonacci series? (enter true or any char)")
    val signPrint = readLine()?.toBoolean() ?: return
    if (signPrint) println("First $n Fibonacci numbers will be output:")
    else println("First $n Fibonacci numbers will not be output.")
    println("$n-number, calculated in loop: ${seriesFibonacci(n, signPrint)}")
    println("_______________")
    println("$n-number, calculated recursively: ${recSeriesFibonacci(n)}")
}

// function with a recursive calculation method
fun recSeriesFibonacci(n: Int): Int {
    var currentNumber: Int = 0
    if (n == 1) return 1
    if (n == 2) return 1
    if (n >= 3) {
        currentNumber = recSeriesFibonacci(n - 1) + recSeriesFibonacci(n - 2)
    }
    return currentNumber
}

// function with calculation in a loop
fun seriesFibonacci(n: Int, signPrint: Boolean): Int {
    when {
        (n == 1 && signPrint) -> {
            println("1-number: 1")
            println("_______________")
            return 1
        }

        (n == 1 && !signPrint) -> return 1
        (n == 2 && signPrint) -> {
            println("2-number: 1")
            println("_______________")
            return 1
        }

        (n == 2 && !signPrint) -> return 1
        (n > 2 && signPrint) -> {
            println("1-number: 1")
            println("2-number: 1")
        }
    }
    var prevNumber = 1
    var prevPrevNumber = 1
    var currentNumber: Int = 0
    for (i in 3..n) {
        currentNumber = prevNumber + prevPrevNumber
        if (signPrint) println("$i-number: $currentNumber")
        prevPrevNumber = prevNumber
        prevNumber = currentNumber
    }
    if (signPrint) println("_______________")
    return currentNumber
}
