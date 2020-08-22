package com.aqube.truecaller.presentation

import androidx.lifecycle.ViewModel
import com.aqube.truecaller.presentation.executor.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val disposable = CompositeDisposable()
    protected val schedulerProvider = SchedulerProvider.getInstance()

    /**
     * Clear all the disposable
     */
    override fun onCleared() {
        disposable.clear()
    }
}
