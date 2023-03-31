abstract class AbstractWarrior : Warrior {
    abstract val maxLevelHealth: Int
    abstract val accuracy: Int
    abstract val weapons: AbstractWeapon
    abstract var currentLevelHealth: Int
    override fun attack(warrior: Warrior) {
        try {
            var sumLoss = 0
            weapons.getPatron().forEach {
                if (accuracy.isChance() && warrior.avoidance.isChance()) {
                    sumLoss += it.receiveCurrentLoss()
                }
            }
            warrior.receiveLoss(sumLoss)
            progressBattle += "Выполнен удар силой $sumLoss | "
        } catch (e: AbstractWeapon.NoAmmoException) {
            weapons.rechargeStore()
            progressBattle += "Выполнена перезарядка | "
        }
    }

    override fun receiveLoss(loss: Int) {
        if (currentLevelHealth > loss) {
            currentLevelHealth -= loss
            progressBattle += "Получен удар силой $loss | "
        } else {
            isKilled = true
            currentLevelHealth = 0
            progressBattle += "Получен смертельный удар | "
        }
    }
}