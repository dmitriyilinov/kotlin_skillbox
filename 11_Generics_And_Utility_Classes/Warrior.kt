interface Warrior {
    var isKilled: Boolean
    val avoidance: Int
    var progressBattle: String
    fun attack(warrior: Warrior)
    fun receiveLoss(loss: Int)
}