package com.droid.diexample.di

import com.droid.diexample.presenter.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(blogRepository = get())
    }
}