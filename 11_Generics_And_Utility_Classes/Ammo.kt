enum class Ammo(
    val loss: Int,
    val chanceCriticalLoss: Int,
    val coefCriticalLoss: Int
) {
    BULLET1(10, 20, 1),
    BULLET2(20, 30, 2),
    BULLET3(30, 40, 3);

    fun receiveCurrentLoss() = if (chanceCriticalLoss.isChance()) {
        loss * coefCriticalLoss
    } else {
        loss
    }
}