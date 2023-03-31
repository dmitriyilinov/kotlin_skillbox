class WarriorSoldier : AbstractWarrior() {
    override val maxLevelHealth = 100
    override val accuracy = 30
    override val weapons = Weapons.createMachineGun()
    override var currentLevelHealth = 100
    override var isKilled = false
    override val avoidance = 30
    override var progressBattle: String = "Солдат с автоматом: "
}