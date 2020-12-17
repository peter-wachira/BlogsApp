package com.droid.diexample.presenter.states

import com.droid.diexample.presenter.models.BlogPresentation

data class BlogViewState(
    val isLoading: Boolean,
    val error: Error?,
    val blogResults: List<BlogPresentation>?
)