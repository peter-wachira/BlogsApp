package com.droid.diexample

import android.app.Application
import com.droid.diexample.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                networkModule,
                viewModelModule,
                // localDataSourceModule,
                remoteDataSourceModule,
                useCaseModule,
                roomModule
            )
        }
    }
}