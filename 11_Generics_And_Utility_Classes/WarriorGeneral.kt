class WarriorGeneral : AbstractWarrior() {
    override val maxLevelHealth = 100
    override val accuracy = 70
    override val weapons = Weapons.createPistol()
    override var currentLevelHealth = 100
    override var isKilled = false
    override val avoidance = 70
    override var progressBattle: String = "Генерал с пистолетом: "
}