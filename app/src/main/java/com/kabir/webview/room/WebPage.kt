package com.kabir.webview.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "urls")
data class WebPage(
    val url: String,
    val timestamp: Long,
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
)

