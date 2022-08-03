package com.example.sampleproject1.Retrofit


import com.google.gson.annotations.SerializedName

data class PostsDataItem(
    @SerializedName("body")
    val body: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("userId")
    val userId: Int = 0
)