package com.aqube.truecallertask.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.aqube.truecaller.presentation.BlogViewModel
import com.aqube.truecaller.presentation.state.Resource
import com.aqube.truecaller.presentation.state.ResourceState
import com.aqube.truecallertask.R
import com.aqube.truecallertask.base.BaseActivity
import com.aqube.truecallertask.extension.bindViewModel
import com.aqube.truecallertask.extension.makeGone
import com.aqube.truecallertask.extension.makeVisible
import com.aqube.truecallertask.injection.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by bindViewModel<BlogViewModel>(lazy { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getBlogs().observe(this, Observer {
            onUiModelChanged(it)
        })

        button.setOnClickListener { viewModel.fetchBlog() }
    }

    private fun onUiModelChanged(resource: Resource<String>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                progressBar.makeGone()
                textView.text = resource.data
            }
            ResourceState.LOADING -> {
                progressBar.makeVisible()
            }
            ResourceState.ERROR -> {
                progressBar.makeGone()
                resource.message?.let { error ->
                    Timber.e(error)
                    findViewById<View?>(android.R.id.content)?.let { view ->
                        Snackbar.make(view, error, Snackbar.LENGTH_INDEFINITE).show()
                    }
                }
            }
        }
    }
}