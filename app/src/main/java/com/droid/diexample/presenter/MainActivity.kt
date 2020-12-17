package com.droid.diexample.presenter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.droid.diexample.R
import com.droid.diexample.domain.model.BlogDomain
import com.droid.diexample.presenter.states.DataState

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import java.lang.StringBuilder


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  val TAG = "AppDebug"
    private  val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogsEvent)
    }


    private fun subscribeObservers(){
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState){
               is DataState.Success<List<BlogDomain>> ->{
                    displayProgressBar(false)
                   appendBlogTitles(dataState.data)
               }
               is DataState.Error ->{
                    displayProgressBar(false)
                   dataState.exception.message?.let { displayError(it) }
               }
               is DataState.Loading ->{
                   displayProgressBar(true)
               }
            }
        })
    }

    private fun displayError(message: String){
        if (message != null) text.text = message else text.text = "Unknown error."
    }

    private fun appendBlogTitles(blogs: List<BlogDomain>){
        val sb = StringBuilder()
        for(blog in  blogs){
            sb.append(blog.title + "\n");
        }
        text.text = sb.toString()
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }


}

