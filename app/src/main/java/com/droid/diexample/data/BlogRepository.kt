package com.droid.diexample.data

import com.droid.diexample.domain.model.BlogDomain
import com.droid.diexample.data.remote.api.BlogApiService
import com.droid.diexample.data.remote.mappers.toDomain
import com.droid.diexample.domain.repository.IBlogsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BlogRepository constructor(
    private val blogApiService: BlogApiService
):IBlogsRepository{
    override suspend fun getBlogs(): Flow<List<BlogDomain>> =
        flow {
            val blogResponse = blogApiService.getBlogs()
            val blogs = mutableListOf<BlogDomain>()
            for (blog in blogResponse.response){
                blogs.add(blog.toDomain())
            }
            emit(blogs)
        }
}