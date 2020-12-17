package com.droid.diexample.domain.usecases

import com.droid.diexample.domain.model.BlogDomain
import com.droid.diexample.domain.repository.IBlogsRepository
import kotlinx.coroutines.flow.Flow

typealias BlogsBaseUseCase = BaseUseCase<Unit, Flow<List<BlogDomain>>>

class BlogsUseCase(private val repository: IBlogsRepository) : BlogsBaseUseCase {
    override suspend fun invoke(parameter: Unit): Flow<List<BlogDomain>> =
        repository.getBlogs()


}