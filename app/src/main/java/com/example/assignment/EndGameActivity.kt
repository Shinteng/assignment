package com.example.assignment

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndGameActivity : AppCompatActivity() {
    private lateinit var nameTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var timeTextView: TextView
    private lateinit var recyclerView: RecyclerView

    private lateinit var participantAdapter: ParticipantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.end_game)

        nameTextView = findViewById(R.id.activityname)
        addressTextView = findViewById(R.id.address)
        timeTextView = findViewById(R.id.time)
        recyclerView = findViewById(R.id.recyclerView3)

        val name: String? = intent.getStringExtra("name")
        val address: String? = intent.getStringExtra("address")
        val time: String? = intent.getStringExtra("time")

        nameTextView.text = name
        addressTextView.text = address
        timeTextView.text = time

        // Set up the RecyclerView and ParticipantAdapter
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        participantAdapter = ParticipantAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@EndGameActivity)
            adapter = participantAdapter
        }


    }
}
