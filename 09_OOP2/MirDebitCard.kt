import kotlin.math.round

class MirDebitCard : DebitCard() {
    override fun replenish(sum: Int) {
        super.replenish(sum)
        addCashBackReplenish(sum, percentCashForReplenish)
    }

    override fun pay(sum: Int): Boolean {
        return if (super.pay(sum)) {
            cashBack += round((sum * percentCashForPay / 100).toDouble()).toInt()
            sumPay += sum
            virtCashBack = round((sum * percentLimitCashForSumPay / 100).toDouble()).toInt()
            if (sumPay >= limitCashForSumPay) {
                cashBack += virtCashBack
            }
            true
        } else false
    }

    companion object {
        const val limitCashForSumPay = 5000
        const val percentLimitCashForSumPay = 5
        const val percentCashForPay = 1
        const val percentCashForReplenish = 0.1
    }
}