package com.droid.diexample.repository

import com.droid.diexample.model.Blog
import com.droid.diexample.retrofit.BlogRetrofit
import com.droid.diexample.retrofit.NetworkMapper
import com.droid.diexample.room.BlogDao
import com.droid.diexample.room.CacheMapper
import com.droid.diexample.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.security.PrivateKey

class MainRepository
constructor(
        private val blogDao: BlogDao,
        private val blogRetrofit: BlogRetrofit,
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