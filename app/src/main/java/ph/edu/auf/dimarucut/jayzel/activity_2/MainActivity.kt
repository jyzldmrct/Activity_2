package ph.edu.auf.dimarucut.jayzel.activity_2

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var player: Character
    private lateinit var enemy: Character
    private lateinit var actionText: TextView
    private lateinit var playerHealth: TextView
    private lateinit var enemyHealth: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val maxHealth = 100
        player = Character("Player", 100, maxHealth)
        enemy = Character("Enemy", 100, maxHealth)

        actionText = findViewById(R.id.actionText)
        playerHealth = findViewById(R.id.playerHealth)
        enemyHealth = findViewById(R.id.enemyHealth)

        val attackButton: ImageButton = findViewById(R.id.attackButton)
        val defendButton: ImageButton = findViewById(R.id.defendButton)
        val healButton: ImageButton = findViewById(R.id.healButton)

        actionText.text = getString(R.string.rpg_turn_based_game)
        actionText.textSize = 50f

        updateUI()

        attackButton.setOnClickListener {
            takeTurn(Action.ATTACK)
        }

        defendButton.setOnClickListener {
            takeTurn(Action.DEFEND)
        }

        healButton.setOnClickListener {
            takeTurn(Action.HEAL)
        }
    }


    private fun takeTurn(action: Action) {
        val playerAction = player.performAction(action, enemy)
        actionText.text = playerAction

        if (enemy.isAlive()) {
            val enemyAction = enemy.performRandomAction(player)
            actionText.append("\n$enemyAction")
        }

        updateUI()

        actionText.textSize = 25f

        checkGameOver()
    }


    private fun updateUI() {
        playerHealth.text = "Player Health: ${player.health}"
        enemyHealth.text = "Enemy Health: ${enemy.health}"
    }

    private fun checkGameOver() {
        if (!player.isAlive()) {
            actionText.append("You lose.")
        } else if (!enemy.isAlive()) {
            actionText.append("\nYou win.")
        }
    }
}
