package com.droid.diexample.di

import com.droid.diexample.data.BlogRepository
import com.droid.diexample.domain.repository.IBlogsRepository
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<IBlogsRepository>{ BlogRepository(blogApiService = get()) }
}