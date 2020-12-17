package com.droid.diexample.presenter

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.droid.diexample.domain.model.BlogDomain
import com.droid.diexample.data.BlogRepository
import com.droid.diexample.presenter.states.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: BlogRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<BlogDomain>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<BlogDomain>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                    is MainStateEvent.GetBlogsEvent -> {
                            mainRepository.getBlogs()
                                    .onEach { dataState ->
                                            _dataState.value = dataState
                                    }
                                    .launchIn(viewModelScope)
                    }

                    is MainStateEvent.None -> {
                            // who cares
                    }
            }
        }
    }

}


sealed class MainStateEvent {

    object GetBlogsEvent : MainStateEvent()

    object None : MainStateEvent()
}

