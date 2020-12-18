package com.droid.diexample.di

import com.droid.diexample.domain.repository.IBlogsRepository
import com.droid.diexample.domain.usecases.BlogsBaseUseCase
import com.droid.diexample.domain.usecases.BlogsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    single(named("blogs")) { provideBlogUseCase(get()) }
}
internal fun provideBlogUseCase(location: IBlogsRepository): BlogsBaseUseCase =
    BlogsUseCase(location)