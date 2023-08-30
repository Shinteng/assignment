package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HostActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var endActivityButton: Button
    private lateinit var participantAdapter: ParticipantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.host_activity)

        recyclerView = findViewById(R.id.recyclerView2)
        endActivityButton = findViewById(R.id.button7)


        val participants = listOf(
            Participant("Participant 1", 100),
            Participant("Participant 2", 200),
            Participant("Participant 3", 150)
        )

        // Set up RecyclerView and its adapter
        participantAdapter = ParticipantAdapter()
        recyclerView.adapter = participantAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        endActivityButton.setOnClickListener {
            // Start the EndGameActivity
            val intent = Intent(this, EndGameActivity::class.java)
            startActivity(intent)
        }
    }


}

