fun main() {
    println("Введите количество телефонных номеров")
    print("n=")
    var n = readLine()?.toIntOrNull() ?: return
// число должно быть положительным
    while (n <= 0) {
        println("Введите положительное число (n > 0)")
        n = readLine()?.toIntOrNull() ?: return
    }
    val listOfNumber = inputNumber(n)
    println("Введенный список:")
    println(listOfNumber)
    println("Номера телефонов из России:")
    println(listOfNumber.filter { it.startsWith("+7") })
    val setOfNumber = listOfNumber.toSet()
    val setOfUniqueNumber = setOfNumber.toSortedSet()
    println("Количество уникальных введенных номеров:")
    println(setOfUniqueNumber.size)
    println("Сумма длин всех номеров телефонов:")
    println(setOfNumber.sumOf { it.length })
    val mapOfNumber = mutableMapOf<String, String>()
    println("Создание телефонной книга")
    setOfUniqueNumber.forEach {
        print("Введите имя для номера телефона $it: ")
        mapOfNumber[it] = readLine().toString()
    }
    println("Телефонная книга:")
    for (entry in mapOfNumber.entries) {
        println("Абонент: ${entry.value}. Номер телефона: ${entry.key}")
    }
    /* в данный момент книга и так отсортирована по номеру телефона,
       так как ключ представления построен последовательно по множеству setOfUniqueNumber = setOfNumber.toSortedSet(),
       которое отсортировано и избавлено от дубликатов */
    println("Телефонная книга, отсортированная по номеру телефона:")
    for (entry in mapOfNumber.toSortedMap(compareBy<String> { it }.thenBy { it }).entries) {
        println("Абонент: ${entry.value}. Номер телефона: ${entry.key}")
    }
    println("Телефонная книга, отсортированная по имени абонента:")
    for (entry in mapOfNumber.toSortedMap(compareBy<String> { mapOfNumber[it] }.thenBy { it }).entries) {
        println("Абонент: ${entry.value}. Номер телефона: ${entry.key}")
    }
}

fun inputNumber(n: Int): List<String> {
    val listOfNumber = mutableListOf<String>()
    var number: String
    val digit = "+0123456789"
    for (i in 1..n) {
        do {
            print("Введите $i-номер:")
            number = readLine().toString()
            if (number.any { it !in digit }) println("Номер должен содержать только цифры и префикс +")
        } while (number.any { it !in digit })
        when {
            (number[0] == '8') -> number = "+7${number.substring(1, number.length)}"
            (number[0] != '+') -> number = "+$number"
        }
        listOfNumber.add(number)
    }
    return listOfNumber
}