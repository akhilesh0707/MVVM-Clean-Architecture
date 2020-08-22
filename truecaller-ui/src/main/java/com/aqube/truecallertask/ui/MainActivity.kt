package com.aqube.truecallertask.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.aqube.truecaller.presentation.BlogUIModel
import com.aqube.truecaller.presentation.BlogViewModel
import com.aqube.truecallertask.R
import com.aqube.truecallertask.base.BaseActivity
import com.aqube.truecallertask.extension.bindViewModel
import com.aqube.truecallertask.extension.makeGone
import com.aqube.truecallertask.extension.makeVisible
import com.aqube.truecallertask.injection.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by bindViewModel<BlogViewModel>(lazy { viewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getBlog().observe(this, Observer {
            onUiModelChanged(it)
        })

        button.setOnClickListener { viewModel.fetchBlog() }
    }

    private fun onUiModelChanged(uiModel: BlogUIModel) {
        when (uiModel) {
            is BlogUIModel.Loading -> {
                progressBar.makeVisible()
            }
            is BlogUIModel.TenthCharacter -> {
                hideProgressBar()
                textViewTenthCharacter.text = uiModel.tenthCharacter
            }
            is BlogUIModel.EveryTenthCharacter -> {
                hideProgressBar()
                textViewEveryTenthCharacter.text = uiModel.everyTenthCharacter
            }
            is BlogUIModel.WordCount -> {
                hideProgressBar()
                textViewWordCount.text = uiModel.wordCount
            }
            is BlogUIModel.Error -> {
                progressBar.makeGone()
                findViewById<View?>(android.R.id.content)?.let { view ->
                    Snackbar.make(view, uiModel.error, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun hideProgressBar() {
        progressBar.makeGone()
    }
}