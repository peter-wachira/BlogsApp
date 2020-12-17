package com.droid.diexample.data

import com.droid.diexample.domain.BlogDomain
import com.droid.diexample.data.remote.api.BlogApiService
import com.droid.diexample.data.local.dao.BlogDao
import com.droid.diexample.data.local.mapper.CacheMapper
import com.droid.diexample.data.remote.mappers.toDomain
import com.droid.diexample.domain.repository.IBlogsRepository
import com.droid.diexample.util.DataState
import kotlinx.coroutines.delay
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