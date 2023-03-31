class Wheel {
    private var currentPressure: Double = 0.0
    fun pumpUp(pressure: Double) {
        when {
            (pressure < 0.0 || pressure > 10.0) -> throw IncorrectPressure()
            (pressure < 1.6 && pressure > 0.0) -> {
                currentPressure = pressure
                throw TooLowPressure()
            }

            (pressure < 10.0 && pressure > 2.5) -> {
                currentPressure = pressure
                throw TooHighPressure()
            }

            else -> currentPressure = pressure
        }
    }

    fun check(): Double {
        when {
            (currentPressure < 0.0 || currentPressure > 10.0) -> throw IncorrectPressure()
            (currentPressure < 1.6 && currentPressure > 0.0) -> throw TooLowPressure()
            (currentPressure < 10.0 && currentPressure > 2.5) -> throw TooHighPressure()
            else -> return currentPressure
        }
    }
}

class TooHighPressure : Throwable(message = "Давление превышает максимально допустимое")
class TooLowPressure : Throwable(message = "Давление слишком низкое")
class IncorrectPressure : Throwable(message = "Недопустимое давление")
