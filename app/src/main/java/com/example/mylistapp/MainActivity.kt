package com.example.mylistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylistapp.model.Scholarship

class MainActivity : AppCompatActivity() {

    private lateinit var rvScholarship: RecyclerView
    private val list = ArrayList<Scholarship>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvScholarship = findViewById(R.id.rvListScholarship)
        rvScholarship.setHasFixedSize(true)

        list.addAll(getListScholarship())
        showRecyclerList()
    }

    private fun getListScholarship(): ArrayList<Scholarship> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val listScholarship = ArrayList<Scholarship>()
        for (i in dataName.indices) {
            val scholarship = Scholarship(dataName[i], dataDescription[i], dataDescription[i])
            listScholarship.add(scholarship)
        }
        return listScholarship
    }

    private fun showRecyclerList() {
        rvScholarship.layoutManager = LinearLayoutManager(this)
        val listScholarshipAdapter = ListScholarshipAdapter(list)
        rvScholarship.adapter = listScholarshipAdapter
    }
}