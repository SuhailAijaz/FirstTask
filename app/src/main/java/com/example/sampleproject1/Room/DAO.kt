package com.example.sampleproject1.Room

import androidx.room.Dao
import androidx.room.Insert
import com.example.sampleproject1.Retrofit.PostsData
import com.example.sampleproject1.Retrofit.PostsDataItem
import retrofit2.Response

@Dao
interface DAO {
    @Insert()
    suspend fun getInsertAPiData(postsDataItem: List<PostsDataItem>)
}