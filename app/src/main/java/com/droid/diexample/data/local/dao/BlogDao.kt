package com.droid.diexample.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droid.diexample.data.local.entity.BlogCacheEntity

@Dao
interface BlogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert(blogEntity : BlogCacheEntity): Long

    @Query("SELECT * FROM  blogs ")
    suspend fun get(): List<BlogCacheEntity>

}