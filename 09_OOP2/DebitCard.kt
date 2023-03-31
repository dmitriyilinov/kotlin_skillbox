abstract class DebitCard : BankCard() {
    override val creditLimit: Int = 0
    override var creditBalance: Int = 0
}