package ph.edu.auf.dimarucut.jayzel.activity_2

import kotlin.random.Random

enum class Action {
    ATTACK, DEFEND, HEAL
}

class Character(val name: String, var health: Int, val maxHealth: Int) {

    private var defendMode = false

    private val actions = Action.values()

    private val damageRange = 10..20
    private val healRange = 10..20

    fun performAction(action: Action, target: Character): String {
        if (!isAlive() || !target.isAlive()) {
            return "Game Over!"
        }
        return when (action) {
            Action.ATTACK -> attack(target)
            Action.DEFEND -> defend()
            Action.HEAL -> heal()
        }
    }

    fun performRandomAction(target: Character): String {
        if (!isAlive() || !target.isAlive()) {
            return "Game Over!"
        }
        val randomAction = actions.random()
        return performAction(randomAction, target)
    }
    private fun attack(target: Character): String {
        if (target.defendMode) {
            target.defendMode = false
            return "$name attacked, but ${target.name} defended!"
        }

        val damage = damageRange.random()
        target.health -= damage
        return "$name attacked ${target.name} for $damage damage!"
    }

    private fun defend(): String {
        defendMode = true
        return "$name is defending!"
    }

    private fun heal(): String {
        if (health >= maxHealth) {
            return "Health is already full!"
        }

        val healAmount = healRange.random()
        health = (health + healAmount).coerceAtMost(maxHealth)
        return "$name healed for $healAmount health!"
    }

    fun isAlive(): Boolean {
        return health > 0
    }


}
