import kotlin.math.round

abstract class BankCard {
    abstract protected val creditLimit: Int
    abstract protected var creditBalance: Int
    protected var ownBalance: Int = 0
    protected var balance: Int = creditBalance + ownBalance
    protected var cashBack: Int = 0
    protected var sumPay: Int = 0
    protected var virtCashBack: Int = 0

    open fun replenish(sum: Int) {
        if ((creditLimit - creditBalance) >= sum) {
            creditBalance += sum
        } else {
            ownBalance += sum - (creditLimit - creditBalance)
            creditBalance = creditLimit
        }
        balance = creditBalance + ownBalance
    }

    protected fun addCashBackReplenish(sum: Int, percentCashForReplenish: Double) {
        cashBack += round((sum * percentCashForReplenish / 100).toDouble()).toInt()
    }


    open fun pay(sum: Int): Boolean {
        return if (sum > balance) {
            false
        } else {
            if ((ownBalance) >= sum) {
                ownBalance -= sum
            } else {
                creditBalance -= sum - ownBalance
                ownBalance = 0
            }
            balance = creditBalance + ownBalance
            true
        }
    }

    fun getInfBalance() = balance

    open fun getAvailableCash() = listOf<Int>(cashBack, creditLimit, creditBalance, ownBalance, balance)

}