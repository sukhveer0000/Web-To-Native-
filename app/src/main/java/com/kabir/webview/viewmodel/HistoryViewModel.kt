package com.kabir.webview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kabir.webview.repository.WebUrlRepository
import com.kabir.webview.room.WebPage
import com.kabir.webview.room.WebUrlDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application): AndroidViewModel(application) {
    private val repository : WebUrlRepository
    val allUrls: LiveData<MutableList<WebPage>>

    init {
        val dao = WebUrlDatabase.getDatabase(application).getWebUrlDao()
        repository = WebUrlRepository(dao)
        allUrls = repository.getAllUrls
    }

    private val _uploadStatus = MutableLiveData<String>()
    val uploadStatus : LiveData<String> get() = _uploadStatus


    fun deleteAllUrls() = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteAllUrls()
    }

    fun uploadHistory() = CoroutineScope(Dispatchers.IO).launch {

        val historyList = allUrls.value ?: mutableListOf()
        val response =  repository.uploadHistory(historyList)

        response.onSuccess { massage ->
            _uploadStatus.postValue(massage)
        }
        response.onFailure { error ->
            _uploadStatus.postValue("Error: ${error.message}")
        }
    }

}