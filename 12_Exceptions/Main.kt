fun main() {
    var pressure: Double
    val wheel = Wheel()
    do {
        println("Введите значение давления, до которого нужно накачать колесо (0 - выход)")
        pressure = readLine()?.toDoubleOrNull() ?: return
        try {
            wheel.pumpUp(pressure)
        } catch (e: TooLowPressure) {
            println("Процедура накачки до $pressure удалась. Эксплуатация невозможна — давление слишком низкое.")
        } catch (e: TooHighPressure) {
            println("Процедура накачки до $pressure удалась. Эксплуатация невозможна — давление слишком высокое.")
        } catch (e: IncorrectPressure) {
            println("Процедура накачки до $pressure не удалась. Эксплуатация невозможна.")
        } finally {
            if (runCatching { wheel.check() }.isSuccess) println("Текущее давление составляет ${wheel.check()}")
        }
    } while (pressure != 0.0)
}