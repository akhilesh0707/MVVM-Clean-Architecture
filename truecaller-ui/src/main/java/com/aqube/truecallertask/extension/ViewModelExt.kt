package com.aqube.truecallertask.extension

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * Get a ViewModel for the [Activity]
 */
inline fun <reified T : ViewModel> AppCompatActivity.bindViewModel(lazyViewModelProviderFactory: Lazy<ViewModelProvider.Factory>): Lazy<T> {
    return lazy {
        ViewModelProviders.of(this, lazyViewModelProviderFactory.value).get(T::class.java)
    }
}

/**
 * Get a ViewModel for the  [Fragment]
 */
inline fun <reified T : ViewModel> Fragment.bindViewModel(lazyViewModelProviderFactory: Lazy<ViewModelProvider.Factory>): Lazy<T> {
    return lazy {
        ViewModelProviders.of(this, lazyViewModelProviderFactory.value).get(T::class.java)
    }
}

