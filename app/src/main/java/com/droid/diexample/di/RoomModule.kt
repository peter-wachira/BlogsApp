package com.droid.diexample.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.droid.diexample.data.local.BlogDatabase
import org.koin.dsl.module


val roomModule = module {
    single {
        Room.databaseBuilder(get(), BlogDatabase::class.java, BlogDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<BlogDatabase>().blogDao() }
}
