package com.aqube.truecaller.presentation.extension

import com.aqube.truecaller.presentation.executor.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * [Observable]
 * Shorthand extension function to run threads for activities and fragments
 */
fun <T> Observable<T>.runOnBackground(schedulerProvider: SchedulerProvider): Observable<T> =
    this.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.mainThread())

/**
 * [Disposable]
 * Add the current disposable to the given [compositeDisposable]
 * @param clear optional parameter to clear the [compositeDisposable] before adding this [Disposable]. Default value is false.
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable, clear: Boolean = false): Boolean {
    if (clear) {
        compositeDisposable.clear()
    }
    return compositeDisposable.add(this)
}