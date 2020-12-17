package com.droid.diexample.presenter

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.droid.diexample.domain.model.BlogDomain
import com.droid.diexample.data.BlogRepository
import com.droid.diexample.domain.usecases.BlogsBaseUseCase
import com.droid.diexample.presenter.mappers.toPresenter
import com.droid.diexample.presenter.models.BlogPresentation
import com.droid.diexample.presenter.states.BlogViewState
import com.droid.diexample.presenter.states.DataState
import com.droid.diexample.presenter.states.Error
import com.droid.diexample.presenter.viewmodels.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
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

