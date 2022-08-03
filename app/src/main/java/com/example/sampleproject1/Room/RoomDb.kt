package com.example.sampleproject1.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sampleproject1.Retrofit.PostsData
import com.example.sampleproject1.Retrofit.PostsDataItem
@Suppress("DEPRECATION")
@Database(entities =[PostsDataItem::class], version = 1, exportSchema = true)
abstract class RoomDb :RoomDatabase(){
    abstract fun dao():DAO

    companion object{
        private var instance:RoomDb?=null
        @Synchronized
        fun getInstance(context: Context):RoomDb {
            if (instance == null) {
                instance = Room.databaseBuilder(context, RoomDb::class.java, "ApiData").build()

            }
            return instance!!

        }
    }


}