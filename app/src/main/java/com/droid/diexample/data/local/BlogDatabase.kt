package com.droid.diexample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.droid.diexample.data.local.entity.BlogCacheEntity
import com.droid.diexample.data.local.dao.BlogDao

@Database(entities = [BlogCacheEntity::class], version = 1)
abstract class BlogDatabase : RoomDatabase(){
    abstract  fun blogDao(): BlogDao

    companion object{
        val DATABASE_NAME : String = "blog_db"
    }
}
