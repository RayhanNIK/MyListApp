package com.example.mylistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mylistapp.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_WEBSITE = "scholarship_website"
    }

    private lateinit var scholarshipWebsite: String

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        scholarshipWebsite = intent.getStringExtra(EXTRA_WEBSITE).toString()

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(scholarshipWebsite)
    }
}