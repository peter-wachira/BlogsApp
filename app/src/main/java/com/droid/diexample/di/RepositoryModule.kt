package com.droid.diexample.di

import com.droid.diexample.repository.MainRepository
import com.droid.diexample.retrofit.BlogRetrofit
import com.droid.diexample.retrofit.NetworkMapper
import com.droid.diexample.room.BlogDao
import com.droid.diexample.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object  RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ) : MainRepository{
        return MainRepository(blogDao,retrofit,cacheMapper,networkMapper)
    }
}