package com.kabir.webview.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WebUrlDao {

    @Insert
    suspend fun insertUrl(url: WebPage)

    @Query("DELETE FROM urls")
    suspend fun deleteAll()

    @Query("SELECT * FROM urls ORDER BY timestamp ASC")
    fun getUrls(): LiveData<MutableList<WebPage>>
}