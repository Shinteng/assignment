package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class JoinLeaveActivity : AppCompatActivity() {

    private lateinit var activityNameTextView: TextView
    private lateinit var timeTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var joinLeaveButton: Button

    private var isJoined = false
    private val membersList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.section)

        activityNameTextView = findViewById(R.id.activityname)
        timeTextView = findViewById(R.id.time)
        addressTextView = findViewById(R.id.address)
        recyclerView = findViewById(R.id.recyclerView3)
        joinLeaveButton = findViewById(R.id.button3)

        populateData()

        joinLeaveButton.setOnClickListener {
            if (isJoined) {
                isJoined = false
                joinLeaveButton.text = "Join"
                membersList.remove("memberName")
            } else {
                isJoined = true
                joinLeaveButton.text = "Leave"
                membersList.add("memberName")
            }
            val adapter = recyclerView.adapter as MemberAdapter
            adapter.notifyDataSetChanged()
        }
        val startButton = findViewById<Button>(R.id.button12)
        startButton.setOnClickListener {
            val intent = Intent(this, MemberActivity::class.java)
            intent.putExtra("userName", "user name")
            startActivity(intent)
        }
    }

    private fun populateData() {
        val intent = intent
        val activityName = intent.getStringExtra("activityName")
        val time = intent.getStringExtra("time")
        val address = intent.getStringExtra("address")

        activityNameTextView.text = activityName
        timeTextView.text = time
        addressTextView.text = address

        val adapter = MemberAdapter(membersList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
