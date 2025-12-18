package com.kabir.webview.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kabir.webview.room.WebPage
import com.kabir.webview.room.WebUrlDao

class WebUrlRepository(private val dao: WebUrlDao) {

    suspend fun insertUrl(url: WebPage){
        dao.insertUrl(url)
    }
    suspend fun deleteAllUrls(){
        dao.deleteAll()
    }

    val getAllUrls: LiveData<MutableList<WebPage>> = dao.getUrls()
}