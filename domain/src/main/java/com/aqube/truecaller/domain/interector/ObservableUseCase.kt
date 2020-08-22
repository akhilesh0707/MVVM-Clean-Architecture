package com.aqube.truecaller.domain.interector

import io.reactivex.Observable

abstract class ObservableUseCase<T, in Params> {

    abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open fun execute(params: Params? = null): Observable<T> {
        return this.buildUseCaseObservable(params)
    }

}
