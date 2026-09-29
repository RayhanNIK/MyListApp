package com.example.mylistapp

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListScholarshipAdapter :
    RecyclerView.Adapter<ListScholarshipAdapter.ListScholarshipViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListScholarshipViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: ListScholarshipViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ListScholarshipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivScholarshipPhoto: ImageView = itemView.findViewById(R.id.ivScholarshipPhoto)
        val ivScholarshipName: TextView = itemView.findViewById(R.id.tvScholarshipName)
        val ivScholarshipDescription: TextView = itemView.findViewById(R.id.tvScholarshipDescription)
    }

}