package com.kabir.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kabir.webview.adapter.UrlHistoryAdapter
import com.kabir.webview.databinding.ActivityHistoryBinding
import com.kabir.webview.viewmodel.HistoryViewModel
import com.kabir.webview.viewmodel.WebUrlViewModel

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var viewModel: HistoryViewModel
    private lateinit var urlAdapter: UrlHistoryAdapter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                ContextCompat.getColor(this,R.color.blue),
                ContextCompat.getColor(this,R.color.black)
            )
        )
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar2)
        urlAdapter = UrlHistoryAdapter()
        setRecyclerView()

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
            setHomeAsUpIndicator(R.drawable.outline_arrow_back_ios_24)
            binding.historyToolbarText.text = "History"
        }

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[HistoryViewModel::class]

        viewModel.allUrls.observe(this){ newList ->
            urlAdapter.update(newList)
        }
        viewModel.uploadStatus.observe(this){ status ->
            Toast.makeText(this, "$status",  Toast.LENGTH_SHORT).show()
        }

    }

    private fun setRecyclerView() = binding.recView.apply {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this@HistoryActivity)
        adapter = urlAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_activity_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.deleteAll ->{
                viewModel.deleteAllUrls()
                true
            }
            R.id.upload ->{
                viewModel.uploadHistory()
                true
            }
            else -> false
        }
    }




}