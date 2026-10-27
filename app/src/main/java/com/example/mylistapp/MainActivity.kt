package com.example.mylistapp

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvScholarship.layoutManager = LinearLayoutManager(this)
            }

            R.id.action_grid -> {
                rvScholarship.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.action_language -> {
                val settingsIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(settingsIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_scholarship, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getListScholarship(): ArrayList<Scholarship> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataWebsite = resources.getStringArray(R.array.data_website)

        val listScholarship = ArrayList<Scholarship>()
        for (i in dataName.indices) {
            val scholarship =
                Scholarship(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataWebsite[i])
            listScholarship.add(scholarship)
        }
        return listScholarship
    }

    private fun showRecyclerList() {
        rvScholarship.layoutManager = LinearLayoutManager(this)
        val listScholarshipAdapter = ListScholarshipAdapter(list)
        rvScholarship.adapter = listScholarshipAdapter

        listScholarshipAdapter.setOnItemClickCallback(
            object : ListScholarshipAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Scholarship) {
                    showSelectedScholarship(data)
                }
            }
        )
    }

    private fun showSelectedScholarship(scholarship: Scholarship) {
        val scholarshipDetailIntent =
            Intent(this@MainActivity, DetailScholarshipActivity::class.java)
        scholarshipDetailIntent.putExtra(DetailScholarshipActivity.EXTRA_NAME,scholarship.name)
        scholarshipDetailIntent.putExtra(DetailScholarshipActivity.EXTRA_DESC, scholarship.description)
        scholarshipDetailIntent.putExtra(DetailScholarshipActivity.EXTRA_PHOTO, scholarship.photo)
        scholarshipDetailIntent.putExtra(DetailScholarshipActivity.EXTRA_WEBSITE, scholarship.website)
        startActivity(scholarshipDetailIntent)
    }

}