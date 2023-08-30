package com.example.assignment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ChangeMarkActivity : AppCompatActivity() {
    private lateinit var nameTextView: TextView
    private lateinit var markTextView: TextView
    private lateinit var winTextView: TextView
    private lateinit var loseTextView: TextView
    private lateinit var plusButton: Button
    private lateinit var minusButton: Button

    private lateinit var participant: Participant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_mark)

        // Get participant details from intent extras
        participant = Participant(
            intent.getStringExtra("name") ?: "",
            intent.getIntExtra("score", 0)
        )

        nameTextView = findViewById(R.id.name)
        markTextView = findViewById(R.id.mark)
        winTextView = findViewById(R.id.textView22)
        loseTextView = findViewById(R.id.textView23)
        plusButton = findViewById(R.id.button8)
        minusButton = findViewById(R.id.button9)

        nameTextView.text = participant.name
        markTextView.text = participant.score.toString()

        plusButton.setOnClickListener {
            participant.score++
            markTextView.text = participant.score.toString()
        }

        minusButton.setOnClickListener {
            participant.score--
            markTextView.text = participant.score.toString()
        }


    }
}
