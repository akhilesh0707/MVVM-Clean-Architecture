package com.aqube.truecallertask.blogs

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aqube.truecaller.presentation.BlogViewModel
import com.aqube.truecaller.presentation.state.Resource
import com.aqube.truecaller.presentation.state.ResourceState
import com.aqube.truecallertask.R
import com.aqube.truecallertask.di.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: BlogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BlogViewModel::class.java)
        viewModel.getBlogs().observe(this, Observer {
            onUiModelChanged(it)
        })
        viewModel.fetchBlog()
    }

    private fun onUiModelChanged(resource: Resource<String>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                textView.text = resource.data
            }
            ResourceState.LOADING -> {

            }
            ResourceState.ERROR -> {
                resource.message?.let { error ->
                    Timber.e(error)
                    findViewById<View?>(android.R.id.content)?.let { view ->
                        Snackbar.make(view, error ?: "", Snackbar.LENGTH_INDEFINITE).show()
                    }
                }
            }
        }
    }
}