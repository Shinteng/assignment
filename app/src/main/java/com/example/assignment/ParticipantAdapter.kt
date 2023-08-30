package com.example.assignment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup

class ParticipantAdapter() : RecyclerView.Adapter<ParticipantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_participant, parent, false)
        return ParticipantViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        val participant = participants[position]

        holder.nameTextView.text = participant.name
        holder.scoreTextView.text = participant.score.toString()

        holder.itemView.setOnClickListener {
            val context: Context = holder.itemView.context
            val intent = Intent(context, ChangeMarkActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return participants.size
    }
}


class ParticipantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    val scoreTextView: TextView = itemView.findViewById(R.id.scoreTextView)
}
