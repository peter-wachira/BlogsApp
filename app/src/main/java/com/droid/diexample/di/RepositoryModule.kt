package com.droid.diexample.di

import com.droid.diexample.data.MainRepository
import com.droid.diexample.data.remote.api.BlogApiService
import com.droid.diexample.data.remote.mapper.NetworkMapper
import com.droid.diexample.data.local.dao.BlogDao
import com.droid.diexample.data.local.mapper.CacheMapper
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
        retrofit: BlogApiService,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ) : MainRepository {
        return MainRepository(blogDao,retrofit,cacheMapper,networkMapper)
    }
}