package com.droid.diexample.retrofit

import com.droid.diexample.model.Blog
import com.droid.diexample.util.EntityMapper
import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun get(): List<BlogNetworkEntity>
}



