package com.droid.diexample.data

import com.droid.diexample.domain.Blog
import com.droid.diexample.data.remote.api.BlogApiService
import com.droid.diexample.data.remote.mapper.NetworkMapper
import com.droid.diexample.data.local.dao.BlogDao
import com.droid.diexample.data.local.mapper.CacheMapper
import com.droid.diexample.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogApiService,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){
    suspend fun getBlogs(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try{
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for(blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}