import kotlin.random.Random

fun main() {
    println("Введите количество воинов в каждой команде (count >= 1)")
    var count = readLine()?.toIntOrNull() ?: return
    while (count < 1) {
        println("Количество воинов должно быть больше 1 (count >= 1)")
        count = readLine()?.toIntOrNull() ?: return
    }

    println("Введите количество итераций сражения (iter >= 1)")
    var countIter = readLine()?.toIntOrNull() ?: return
    while (countIter < 1) {
        println("Количество итераций сражения должно быть больше 1 (iter >= 1)")
        countIter = readLine()?.toIntOrNull() ?: return
    }

    var currentStatus = true
    var i = 1
    val battle = Battle()
    battle.team1.createTeamWarrior(count)
    battle.team2.createTeamWarrior(count)

    println("На поле сражаются:")
    println((battle.getBattleState() as BattleState.Progress).progressInfo)
    println("..................")
    while (currentStatus && i <= countIter) {
        println("Итерация $i")
        battle.iterateBattle()
        when (battle.getBattleState()) {
            is BattleState.Victory1Team -> {
                println((battle.getBattleState() as BattleState.Victory1Team).progressInfo)
                println("Победа команды1!")
                println("..................")
                currentStatus = false
            }

            is BattleState.Victory2Team -> {
                println((battle.getBattleState() as BattleState.Victory2Team).progressInfo)
                println("Победа команды2!")
                println("..................")
                currentStatus = false
            }

            is BattleState.Nobody -> {
                println((battle.getBattleState() as BattleState.Nobody).progressInfo)
                println("Победы нет. Ничья!")
                println("..................")
                currentStatus = false
            }

            is BattleState.Progress -> {
                println((battle.getBattleState() as BattleState.Progress).progressInfo)
                println("Битва продолжается!")
                println("..................")
                i++
            }
        }
    }
    println("Ход сражения команды1:")
    println(battle.team1.progressTeam)
    battle.team1.teamWarrior.forEach { println(it.progressBattle) }
    println("..................")
    println("Ход сражения команды2:")
    println(battle.team2.progressTeam)
    battle.team2.teamWarrior.forEach { println(it.progressBattle) }
}

fun Int.isChance(): Boolean {
    return when {
        (this <= 0) -> {
            false
        }

        (this in 1..99) -> {
            val listChance = mutableListOf<Int>(100)
            for (i in 1..this) listChance.add(1)
            for (i in this + 1..100) listChance.add(0)
            listChance[Random.nextInt(1, 101)] == 1
        }

        else -> {
            true
        }
    }
}
