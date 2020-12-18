package com.droid.diexample.presenter.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.droid.diexample.domain.usecases.BlogsBaseUseCase
import com.droid.diexample.presenter.mappers.toPresenter
import com.droid.diexample.presenter.models.BlogPresentation
import com.droid.diexample.presenter.states.BlogViewState
import com.droid.diexample.presenter.states.Error
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect


@ExperimentalCoroutinesApi
class MainViewModel
constructor(
    private val blogRepository: BlogsBaseUseCase
) : BaseViewModel() {
    private var getBlogs:Job? = null
    private val _blogViewState: MutableLiveData<BlogViewState> = MutableLiveData()

    val blogState: LiveData<BlogViewState>
        get() = _blogViewState
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = exception.message.toString()
        onGetBlogsError(message)
    }

    private fun onGetBlogsError(message: String) {
        _blogViewState.value = _blogViewState.value?.copy(isLoading = false,error = Error(message))
    }

    private fun onGetBlogsLoading(){
        _blogViewState.value = _blogViewState.value?.copy(isLoading = true)
    }
    private fun onGetBlogsComplete(result:List<BlogPresentation>){
        _blogViewState.value = _blogViewState.value?.copy(isLoading = false,blogResults = result )
    }

    override fun onCleared() {
        super.onCleared()
        getBlogs?.cancel()
    }

    fun executeGetBlogs(){
        getBlogs?.cancel()
        getBlogs = launchCoroutine {
            delay(500)
            onGetBlogsLoading()
            blogRepository().collect {
                val blogs = it.map { blogDomain -> blogDomain.toPresenter() }
                onGetBlogsComplete(blogs)
            }
        }
    }

    init {
        _blogViewState.value =
            BlogViewState(
                isLoading = false,
                error = null,
                blogResults = null
            )
    }

}

