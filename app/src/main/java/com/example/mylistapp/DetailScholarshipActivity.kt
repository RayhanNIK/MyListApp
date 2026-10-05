package com.example.mylistapp

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mylistapp.databinding.ActivityDetailScholarshipBinding

class DetailScholarshipActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "scholarship_name"
        const val EXTRA_DESC = "scholarship_description"
    }

    private lateinit var binding: ActivityDetailScholarshipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailScholarshipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayScholarshipDetail()
    }

    private fun displayScholarshipDetail() {
        binding.tvScholarshipDetailName.text = intent.getStringExtra(EXTRA_NAME)
        binding.tvScholarshipDetailDesc.text = intent.getStringExtra(EXTRA_DESC)
    }
}