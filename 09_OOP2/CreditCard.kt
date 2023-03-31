abstract class CreditCard : BankCard() {
    override val creditLimit: Int = 10000
    override var creditBalance: Int = creditLimit
}