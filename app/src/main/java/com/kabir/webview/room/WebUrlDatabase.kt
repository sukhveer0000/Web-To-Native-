package com.kabir.webview.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [WebPage::class], version = 1)
abstract class WebUrlDatabase: RoomDatabase() {
    abstract fun getWebUrlDao(): WebUrlDao

    companion object{
        private var INSTANCE : WebUrlDatabase? = null

        fun getDatabase(context: Context): WebUrlDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        WebUrlDatabase::class.java,
                        "webUrlDb")
                        .build()
                }
            }
            return INSTANCE!!
        }

    }
}