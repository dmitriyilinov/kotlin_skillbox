fun main() {
    var tel: TV
    var variant: Int
    do {
        println("Введите вариант бренда производителя телевизора (1 - LG, 2 - Samsung, 3 - Xiaomi, 4 - Philips, 5 - HYUNDAI")
        println("Если вы хотите завершить выполнение, введите 0")
        variant = readLine()?.toIntOrNull() ?: return
        while (variant !in 0..5) {
            println("Введите вариант бренда производителя телевизора (1 - LG, 2 - Samsung, 3 - Xiaomi, 4 - Philips, 5 - HYUNDAI")
            println("Если вы хотите завершить выполнение, введите 0")
            variant = readLine()?.toIntOrNull() ?: return
        }
        if (variant in 1..5) {
            tel = TV(
                BrandTV.getBrand(variant),
                BrandTV.getModel(BrandTV.getBrand(variant)),
                BrandTV.getDiagonal(BrandTV.getBrand(variant))
            )
            printInfo(tel)
            println("Выберите вариант подписки дополнительного канала: (1-NTV+, 2-FOOTBULL+, 3-CHILD+/0 -не нужна)")
            var subscription = readLine()?.toIntOrNull() ?: return
            while (subscription !in 0..4) {
                println("Выберите вариант подписки дополнительного канала: (1-NTV+, 2-FOOTBULL+, 3-CHILD+/0 -не нужна)")
                subscription = readLine()?.toIntOrNull() ?: return
            }
            if (subscription != 0) tel.applySubscription(subscription)
            tel.printAllListChannel()
            tel.on()
            tel.upVolume(inputValue("Volume", tel.countChannel))
            tel.downVolume(inputValue("Volume", tel.countChannel))
            tel.selectChannel(inputValue("Channel", tel.countChannel))
            tel.off()
            tel.selectChannel(inputValue("Channel", tel.countChannel))
            println("Browsing up:")
            for (i in 1..tel.countChannel) {
                tel.upChannel()
                Thread.sleep(500)
            }
            Thread.sleep(2000)
            println("Browsing down:")
            for (i in 1..tel.countChannel) {
                tel.downChannel()
                Thread.sleep(500)
            }
        }
    } while (variant != 0)
}

fun inputValue(section: String, count: Int): Int {
    var n = 0
    when {
        (section == "Volume") -> {
            println("Введите число, на которое изменить громкость (0 < n < 30)")
            n = readLine()?.toIntOrNull() ?: return 0
            while (n !in 1..30) {
                println("Введите число, на которое изменить громкость (0 < n < 30)")
                n = readLine()?.toIntOrNull() ?: return 0
            }
        }

        (section == "Channel") -> {
            println("Введите номер канала (0 < n < $count)")
            n = readLine()?.toIntOrNull() ?: return 0
            while (n !in 1..count) {
                println("Введите номер канала (0 < n < $count)")
                n = readLine()?.toIntOrNull() ?: return 0
            }
        }
    }
    return n
}

fun printInfo(tel: TV) {
    println("TV.brand - ${tel.brand}, TV.model - ${tel.model}, TV.diagonal - ${tel.diagonal}")
}