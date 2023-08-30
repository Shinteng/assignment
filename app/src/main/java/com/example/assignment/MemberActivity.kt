package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MemberActivity : AppCompatActivity() {

    private lateinit var winTextView: TextView
    private lateinit var loseTextView: TextView
    private lateinit var addButton: Button
    private lateinit var minusButton: Button

    private var winCount = 0
    private var loseCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.member_activity)

        winTextView = findViewById(R.id.textView17)
        loseTextView = findViewById(R.id.textView18)
        addButton = findViewById(R.id.button4)
        minusButton = findViewById(R.id.button5)

        updateCounts()

        addButton.setOnClickListener {
            winCount++
            updateCounts()
        }

        minusButton.setOnClickListener {
            if (loseCount > 0) {
                loseCount--
            }
            updateCounts()
        }


        val endGameButton: Button = findViewById(R.id.button6)
        endGameButton.setOnClickListener {

            val intent = Intent(this, HostActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateCounts() {
        winTextView.text = "Win: $winCount"
        loseTextView.text = "Lose: $loseCount"
    }
}

