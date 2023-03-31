import kotlin.math.round

class MirCreditCard : CreditCard() {

    override fun replenish(sum: Int) {
        super.replenish(sum)
        addCashBackReplenish(sum, percentCashForReplenish)
    }

    companion object {
        const val percentCashForReplenish = 0.005
    }
}