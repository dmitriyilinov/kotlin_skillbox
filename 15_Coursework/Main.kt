import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

fun main() = runBlocking {
    val warehouse = Warehouse()
    val countUnloadPort = 3
    val countLoadPort = 2

    val queueUnloadTrucks = produceUnloadTruck()
    val queueLoadTrucks = produceLoadTruck()
    val jobUnloadTrucks = launch {
        repeat(countUnloadPort) {
            unloadTruckProcessor(it + 1, queueUnloadTrucks, warehouse)
        }
    }

    val jobLoadTrucks = launch {
        repeat(countLoadPort) {
            loadTruckProcessor(it + 1, queueLoadTrucks, warehouse)
        }
    }

    delay(30000)
    queueUnloadTrucks.cancel()
    queueLoadTrucks.cancel()
    jobUnloadTrucks.join()
    jobLoadTrucks.join()
    printContentWarehouse(warehouse)
}

fun CoroutineScope.produceUnloadTruck(): ReceiveChannel<Trucks> = produce {
    while (true) {
        val truck = Trucks()
        truck.createUnloadTruck()
        delay((100..1000).random().toLong())
        send(truck)
    }
}

fun CoroutineScope.produceLoadTruck(): ReceiveChannel<Trucks> = produce {
    while (true) {
        val truck = Trucks()
        truck.createLoadTruck()
        send(truck)
    }
}

fun CoroutineScope.unloadTruckProcessor(port: Int, trucks: ReceiveChannel<Trucks>, warehouse: Warehouse) = launch {
    for (truck in trucks) {
        println("Окно разгрузки № $port")
        printTruck(truck)
        warehouse.putProduct(truck.unloadTruck())
    }
}

fun CoroutineScope.loadTruckProcessor(port: Int, trucks: ReceiveChannel<Trucks>, warehouse: Warehouse) = launch {
    for (truck in trucks) {
        var weightLoadTruck = 0
        try {
            withTimeout(10000) {
                while (truck.typeTrucks.capacity > weightLoadTruck) {
                    val productForLoad = warehouse
                        .getProduct(
                            TypeProduct.values()[(0..3).random()],
                            truck.typeTrucks.capacity - weightLoadTruck
                        )
                    productForLoad.forEach {
                        weightLoadTruck += it.value
                    }
                    truck.loadTruck(productForLoad)
                    delay(1000)
                }
                println("Окно загрузки № $port отправлен полный автомобиль")
                printTruck(truck)
            }
        } catch (t: TimeoutCancellationException) {
            println("Окно загрузки № $port отправлен неполный автомобиль после длительного ожидания")
            printTruck(truck)
        }
    }
}

fun printTruck(truck: Trucks) {
    println("Автомобиль ${truck.typeTrucks} - вместимостью ${truck.typeTrucks.capacity} - загружен  на ${truck.storage.values.sumOf { it }}:")
    truck.storage.toSortedMap().forEach { println("Товар ${it.key} - вес ${it.value}") }
    println("-----------------------------")
}

fun printContentWarehouse(warehouse: Warehouse) {
    println("Склад:")
    warehouse.storage.toSortedMap().forEach { println("Товар ${it.key} - вес ${it.value}") }
    println("-----------------------------")
}
