package com.example.mylistapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
        setupSearchView(menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun setupSearchView(menu: Menu?) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterScholarshipList(newText)
                return true
            }

        })

    }

    private fun filterScholarshipList(query: String?) {
        val filteredList = ArrayList<Scholarship>()
        if (!query.isNullOrBlank()) {
            val lowerCaseQuery = query.lowercase()
            for (item in list) {
                if (item.name.lowercase().contains(lowerCaseQuery)) {
                    filteredList.add(item)
                }
            }
        } else {
            // If query is empty, show the original full list
            filteredList.addAll(list)
        }

        val adapter = rvScholarship.adapter as ListScholarshipAdapter
        adapter.updateList(filteredList)
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