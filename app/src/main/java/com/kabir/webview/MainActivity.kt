package com.kabir.webview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.kabir.webview.adapter.ViewPagerAdapter
import com.kabir.webview.databinding.ActivityMainBinding
import com.kabir.webview.room.WebPage
import com.kabir.webview.utils.Constants
import com.kabir.webview.viewmodel.WebUrlViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var viewModel: WebUrlViewModel
    private val imageList = listOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                ContextCompat.getColor(this, R.color.blue),
                ContextCompat.getColor(this, R.color.black)
            )
        )
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbarText.text = "WebToNative"

        viewModel = ViewModelProvider(this)[WebUrlViewModel::class.java]

        adapter = ViewPagerAdapter(imageList)
        binding.viewPager.adapter = adapter
        binding.wormDotsIndicator.attachTo(binding.viewPager)

        binding.openUrlButton.setOnClickListener {
            openUrl()

        }

    }

    private fun openUrl() {
        val url = binding.enterUrl.text.toString().trim()
        val timestamp = System.currentTimeMillis()


        if (url.isEmpty()) {
            binding.enterUrl.error = "Empty Url!"
            return
        }
        var formatedUrl = url

        if (!formatedUrl.startsWith("http://") && !formatedUrl.startsWith("https://")) {
            formatedUrl = "https://$formatedUrl"
        }

        if (Patterns.WEB_URL.matcher(formatedUrl).matches()) {

            val webUrl = WebPage(formatedUrl, timestamp)
            viewModel.insertUrl(webUrl)

            val intent = Intent(this@MainActivity, WebPageActivity::class.java)
            intent.putExtra(Constants.URL_KEY, formatedUrl)
            startActivity(intent)
        } else {
            Toast.makeText(this@MainActivity, "Invalid Url input!", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.hitory_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu -> {
                startActivity(Intent(this@MainActivity, HistoryActivity::class.java))
                return true
            }

            else -> false
        }
    }

    override fun onResume() {
        super.onResume()
        //checking that we coming by close button
        val shouldClear = intent.getBooleanExtra(Constants.CLEAR_TEXT, false)
        if (shouldClear) {
            binding.enterUrl.text?.clear()
            //removing this extra so that if screen rotated and edit field should not clear
            intent.removeExtra(Constants.CLEAR_TEXT)
        }

    }


}


