package com.kabir.webview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.kabir.webview.repository.WebUrlRepository
import com.kabir.webview.room.WebPage
import com.kabir.webview.room.WebUrlDao
import com.kabir.webview.room.WebUrlDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WebUrlViewModel(application: Application): AndroidViewModel(application) {
    val allUrls : LiveData<MutableList<WebPage>>
    val repository : WebUrlRepository

    init {
        val dao = WebUrlDatabase.getDatabase(application).getWebUrlDao()
        repository = WebUrlRepository(dao)
        allUrls = repository.getAllUrls
    }

    fun insertUrl(url: WebPage) = CoroutineScope(Dispatchers.IO).launch {
        repository.insertUrl(url)
    }

    fun deleteAllUrls() = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteAllUrls()
    }
}