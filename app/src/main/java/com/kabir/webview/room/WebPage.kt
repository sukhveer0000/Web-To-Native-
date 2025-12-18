package com.kabir.webview.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "urls")
data class WebPage(
    val url: String,
    val timestamp: Long,
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
)

