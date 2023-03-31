class WarriorCaptain : AbstractWarrior() {
    override val maxLevelHealth = 100
    override val accuracy = 50
    override val weapons = Weapons.createGun()
    override var currentLevelHealth = 100
    override var isKilled = false
    override val avoidance = 50
    override var progressBattle: String = "Капитан с винтовкой: "
}