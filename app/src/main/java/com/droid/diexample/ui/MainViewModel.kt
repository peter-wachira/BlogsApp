package com.droid.diexample.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.droid.diexample.domain.Blog
import com.droid.diexample.data.MainRepository
import com.droid.diexample.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Blog>>>
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

