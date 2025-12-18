package com.kabir.webview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.activity.SystemBarStyle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kabir.webview.databinding.ActivityWebPageBinding
import com.kabir.webview.utils.Constants

class WebPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebPageBinding
    private var url: String = ""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                ContextCompat.getColor(this,R.color.blue),
                ContextCompat.getColor(this,R.color.black)
            )
        )
        setSupportActionBar(binding.toolbar4)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
            setHomeAsUpIndicator(R.drawable.outline_arrow_back_ios_24)
        }

        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        if (intent.hasExtra(Constants.URL_KEY)){
            url = intent.getStringExtra(Constants.URL_KEY)!!
            binding.etUrl.setText(url)
            binding.webView.loadUrl(url)
        }


        onBackPressedDispatcher.addCallback(this) {
            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.webpage_close_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.close ->{

                val intent = Intent(this@WebPageActivity, MainActivity::class.java)

                //FLAG_ACTIVITY_CLEAR_TOP return the existing mainActivity
                //And clear all other other activities from the backStack
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.putExtra(Constants.CLEAR_TEXT,true)
                startActivity(intent)
                finish()
                true
            }
            else -> false
        }
    }
}