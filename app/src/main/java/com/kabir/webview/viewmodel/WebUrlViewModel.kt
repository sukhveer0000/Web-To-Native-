package com.kabir.webview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kabir.webview.repository.WebUrlRepository
import com.kabir.webview.room.WebPage
import com.kabir.webview.room.WebUrlDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WebUrlViewModel(application: Application): AndroidViewModel(application) {

    private val repository : WebUrlRepository

    init {
        val dao = WebUrlDatabase.getDatabase(application).getWebUrlDao()
        repository = WebUrlRepository(dao)
    }

    fun insertUrl(url: WebPage) = CoroutineScope(Dispatchers.IO).launch {
        repository.insertUrl(url)
    }

}