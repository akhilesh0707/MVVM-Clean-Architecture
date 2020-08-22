package com.aqube.truecaller.presentation.extension

import com.aqube.truecaller.presentation.executor.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * [Single]
 * Shorthand extension function to run threads for activities and fragments
 */
fun <T> Single<T>.subscribeOnIoScheduler(schedulerProvider: SchedulerProvider): Single<T> =
    this.subscribeOn(schedulerProvider.io())

fun <T> Single<T>.observeOnMainScheduler(schedulerProvider: SchedulerProvider): Single<T> =
    this.observeOn(schedulerProvider.mainThread())

fun <T> Single<T>.runOnBackground(schedulerProvider: SchedulerProvider): Single<T> =
    this.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.mainThread())

/**
 * [Maybe]
 * Shorthand extension function to run threads for activities and fragments
 */
fun <T> Maybe<T>.subscribeOnIoScheduler(schedulerProvider: SchedulerProvider): Maybe<T> =
    this.subscribeOn(schedulerProvider.io())

fun <T> Maybe<T>.observeOnMainScheduler(schedulerProvider: SchedulerProvider): Maybe<T> =
    this.observeOn(schedulerProvider.mainThread())

fun <T> Maybe<T>.runOnBackground(schedulerProvider: SchedulerProvider): Maybe<T> =
    this.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.mainThread())

/**
 * [Completable]
 * Shorthand extension function to run threads for activities and fragments
 */
fun Completable.subscribeOnIoScheduler(schedulerProvider: SchedulerProvider): Completable =
    this.subscribeOn(schedulerProvider.io())

fun Completable.observeOnMainScheduler(schedulerProvider: SchedulerProvider): Completable =
    this.observeOn(schedulerProvider.mainThread())

fun Completable.runOnBackground(schedulerProvider: SchedulerProvider): Completable =
    this.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.mainThread())

/**
 * [Observable]
 * Shorthand extension function to run threads for activities and fragments
 */
fun <T> Observable<T>.subscribeOnIoScheduler(schedulerProvider: SchedulerProvider): Observable<T> =
    this.subscribeOn(schedulerProvider.io())

fun <T> Observable<T>.observeOnMainScheduler(schedulerProvider: SchedulerProvider): Observable<T> =
    this.observeOn(schedulerProvider.mainThread())

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