package com.example.mylistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mylistapp.model.Scholarship

class ListScholarshipAdapter(
    private var listScholarship: ArrayList<Scholarship>
) : RecyclerView.Adapter<ListScholarshipAdapter.ListScholarshipViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListScholarshipViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_scholarship, parent, false)
        return ListScholarshipViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ListScholarshipViewHolder,
        position: Int
    ) {
        val (name, description, photo) = listScholarship[position]
        holder.ivScholarshipPhoto.setImageResource(photo)
        holder.tvScholarshipName.text = name
        holder.tvScholarshipDescription.text = description

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listScholarship[position])
        }
    }

    override fun getItemCount(): Int = listScholarship.size

    class ListScholarshipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivScholarshipPhoto: ImageView = itemView.findViewById(R.id.ivScholarshipPhoto)
        val tvScholarshipName: TextView = itemView.findViewById(R.id.tvScholarshipName)
        val tvScholarshipDescription: TextView =
            itemView.findViewById(R.id.tvScholarshipDescription)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun updateList(newList: List<Scholarship>) {
        this.listScholarship = newList as ArrayList<Scholarship>
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Scholarship)
    }

}