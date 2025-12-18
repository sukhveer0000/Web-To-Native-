package com.kabir.webview.repository

import androidx.lifecycle.LiveData
import com.kabir.webview.retrofit.RetrofitService
import com.kabir.webview.room.WebPage
import com.kabir.webview.room.WebUrlDao

class WebUrlRepository(private val dao: WebUrlDao) {

    val getAllUrls: LiveData<MutableList<WebPage>> = dao.getUrls()

    suspend fun insertUrl(url: WebPage){
        dao.insertUrl(url)
    }
    suspend fun deleteAllUrls(){
        dao.deleteAll()
    }

    suspend fun uploadHistory(history: MutableList<WebPage>) : Result<String>{
        return try {
            val response = RetrofitService.api.uploadHistory(history)
            if (response.isSuccessful){
                Result.success("History successfully uploaded")
            }else{
                Result.failure(Exception("Server Error: ${response.code()}"))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}