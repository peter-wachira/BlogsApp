package com.droid.diexample.di

import com.droid.diexample.model.Blog
import com.droid.diexample.retrofit.BlogNetworkEntity
import com.droid.diexample.retrofit.BlogRetrofit
import com.droid.diexample.retrofit.NetworkMapper
import com.droid.diexample.util.EntityMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
   fun provideGsonBulder(): Gson{
       return  GsonBuilder()
           .excludeFieldsWithoutExposeAnnotation()
           .create()
   }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun  provideBlogService(retrofit: Retrofit.Builder): BlogRetrofit{
        return retrofit
            .build()
            .create(BlogRetrofit::class.java)
    }
}