package com.droid.diexample.domain.usecases

import com.droid.diexample.domain.BlogDomain
import com.droid.diexample.domain.repository.IBlogsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

typealias BlogsBaseUseCase = BaseUseCase<Unit, Flow<List<BlogDomain>>>

class BlogsUseCase(private val repository: IBlogsRepository) : BlogsBaseUseCase {
    override suspend fun invoke(parameter: Unit): Flow<List<BlogDomain>> =
        repository.getBlogs()


}