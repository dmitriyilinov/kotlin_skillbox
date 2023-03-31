sealed class BattleState {
    data class Progress(val team1: Team, val team2: Team) : BattleState() {
        val progressInfo = """
            |Команда1: воинов: ${team1.teamWarrior.size}; суммарное здоровье команды: ${team1.teamWarrior.sumOf { it.currentLevelHealth }}
            |Команда2: воинов: ${team2.teamWarrior.size}; суммарное здоровье команды: ${team2.teamWarrior.sumOf { it.currentLevelHealth }}
            """.trimMargin()
    }

    data class Victory1Team(val team1: Team, val team2: Team) : BattleState() {
        val progressInfo = """
            |Команда1: воинов: ${team1.teamWarrior.size}; суммарное здоровье команды: ${team1.teamWarrior.sumOf { it.currentLevelHealth }}
            |Команда2: воинов: ${team2.teamWarrior.size}; суммарное здоровье команды: ${team2.teamWarrior.sumOf { it.currentLevelHealth }}
            """.trimMargin()
    }

    data class Victory2Team(val team1: Team, val team2: Team) : BattleState() {
        val progressInfo = """
            |Команда1: воинов: ${team1.teamWarrior.size}; суммарное здоровье команды: ${team1.teamWarrior.sumOf { it.currentLevelHealth }}
            |Команда2: воинов: ${team2.teamWarrior.size}; суммарное здоровье команды: ${team2.teamWarrior.sumOf { it.currentLevelHealth }}
            """.trimMargin()
    }

    data class Nobody(val team1: Team, val team2: Team) : BattleState() {
        val progressInfo = """
            |Команда1: воинов: ${team1.teamWarrior.size}; суммарное здоровье команды: ${team1.teamWarrior.sumOf { it.currentLevelHealth }}
            |Команда2: воинов: ${team2.teamWarrior.size}; суммарное здоровье команды: ${team2.teamWarrior.sumOf { it.currentLevelHealth }}
            """.trimMargin()
    }
}