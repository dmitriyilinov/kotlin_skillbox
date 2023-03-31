import kotlin.random.Random

class Team() {
    val teamWarrior = mutableListOf<AbstractWarrior>()
    lateinit var progressTeam: String
    fun createTeamWarrior(countTeamWarrior: Int) {
        progressTeam = ""
        for (i in 1..countTeamWarrior) {
            when (Random.nextInt(1, 101)) {
                in 1..10 -> {
                    val warrior = WarriorGeneral()
                    warrior.weapons.rechargeStore()
                    teamWarrior.add(warrior)
                }

                in 11..40 -> {
                    val warrior = WarriorCaptain()
                    warrior.weapons.rechargeStore()
                    teamWarrior.add(warrior)
                }

                else -> {
                    val warrior = WarriorSoldier()
                    warrior.weapons.rechargeStore()
                    teamWarrior.add(warrior)
                }
            }
        }
    }
}