class Battle() {
    val team1 = Team()
    val team2 = Team()
    private var finalBattle: Boolean = false

    fun getBattleState(): BattleState {
        when {
            (team1.teamWarrior.size == 0 && team2.teamWarrior.size == 0) -> {
                finalBattle = true
                return BattleState.Nobody(team1, team2)
            }

            (team1.teamWarrior.size > 0 && team2.teamWarrior.size == 0) -> {
                finalBattle = true
                return BattleState.Victory1Team(team1, team2)
            }

            (team1.teamWarrior.size == 0 && team2.teamWarrior.size > 0) -> {
                finalBattle = true
                return BattleState.Victory2Team(team1, team2)
            }
        }
        return BattleState.Progress(team1, team2)
    }

    fun iterateBattle() {
        when ((1..2).random()) {
            1 -> {
                val warrior1 = team1.teamWarrior.random()
                val warrior2 = team2.teamWarrior.random()
                warrior1.attack(warrior2)
                if (warrior2.isKilled) {
                    team2.progressTeam += "\n"
                    team2.progressTeam += warrior2.progressBattle
                    team2.teamWarrior.remove(warrior2)
                }
            }

            2 -> {
                val warrior1 = team1.teamWarrior.random()
                val warrior2 = team2.teamWarrior.random()
                warrior2.attack(warrior1)
                if (warrior1.isKilled) {
                    team1.progressTeam += "\n"
                    team1.progressTeam += warrior1.progressBattle
                    team1.teamWarrior.remove(warrior1)
                }
            }
        }
    }
}