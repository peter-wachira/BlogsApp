package com.droid.diexample.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.droid.diexample.R
import com.droid.diexample.presenter.models.BlogPresentation
import com.droid.diexample.presenter.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {
    private val TAG = "AppDebug"
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.executeGetBlogs()
        observeViewState()
    }

    private fun observeViewState() {
        mainViewModel.blogState.observe(this, {states ->
            if (states.isLoading) {
                displayProgressBar(true)
            }
            states.blogResults?.let { blogs ->
                appendBlogTitles(blogs)
            }
            states.error?.run {
                displayError(this.message)
            }
        })
    }

    private fun displayError(message: String) {
        if (message != null) text.text = message else text.text = "Unknown error."
    }

    private fun appendBlogTitles(blogs: List<BlogPresentation>) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append(blog.title + "\n");
        }
        text.text = sb.toString()
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }


}

