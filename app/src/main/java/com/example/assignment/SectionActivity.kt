package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SectionActivity : AppCompatActivity() {

    private lateinit var activityNameTextView: TextView
    private lateinit var timeTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.section)

        activityNameTextView = findViewById(R.id.activityname)
        timeTextView = findViewById(R.id.time)
        addressTextView = findViewById(R.id.address)
        recyclerView = findViewById(R.id.recyclerView3)

        val startButton = findViewById<Button>(R.id.button12)
        startButton.setOnClickListener {
            val intent = Intent(this, MemberActivity::class.java)
            intent.putExtra("userName", "user name")
            startActivity(intent)
        }

        populateData()
    }

    private fun populateData() {
        val intent = intent
        val activityName = intent.getStringExtra("activityName")
        val time = intent.getStringExtra("time")
        val address = intent.getStringExtra("address")
        val membersList = intent.getStringArrayListExtra("members")

        activityNameTextView.text = activityName
        timeTextView.text = time
        addressTextView.text = address

        val adapter = membersList?.let { MemberAdapter(it) }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val shareButton = findViewById<Button>(R.id.button3)
        shareButton.setOnClickListener {
            val activityName = activityNameTextView.text.toString()
            val shareMessage = "Join this activity: $activityName"

            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, "Share via"))
        }
    }

}
