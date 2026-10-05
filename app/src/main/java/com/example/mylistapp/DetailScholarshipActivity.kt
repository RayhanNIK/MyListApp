package com.example.mylistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mylistapp.databinding.ActivityDetailScholarshipBinding

class DetailScholarshipActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailScholarshipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailScholarshipBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}