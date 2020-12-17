package com.droid.diexample.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BlogResponse(
    val response:List<Blog>
)
data class Blog(
    @SerializedName("pk")
    @Expose
    var id: Int,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("body")
    @Expose
    var body: String,

    @SerializedName("image")
    @Expose
    var image: String,

    @SerializedName("category")
    @Expose
    var category: String
)
