package com.aqube.truecallertask.extension

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * Get a ViewModel for the [Activity]
 */
inline fun <reified T : ViewModel> AppCompatActivity.bindViewModel(lazyViewModelProviderFactory: Lazy<ViewModelProvider.Factory>): Lazy<T> {
    return lazy {
        ViewModelProvider(this, lazyViewModelProviderFactory.value).get(T::class.java)
    }
}

