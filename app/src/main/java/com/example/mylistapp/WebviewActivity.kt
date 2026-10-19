package com.example.mylistapp

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebviewActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_WEBSITE = "scholarship_website"
    }

    private lateinit var scholarshipWebsite: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        scholarshipWebsite = intent.getStringExtra(EXTRA_WEBSITE).toString()

        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.google.com")
    }
}