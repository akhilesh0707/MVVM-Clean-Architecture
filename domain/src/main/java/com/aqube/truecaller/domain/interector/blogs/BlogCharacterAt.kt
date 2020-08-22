package com.aqube.truecaller.domain.interector.blogs

import com.aqube.truecaller.domain.interector.ObservableUseCase
import com.aqube.truecaller.domain.repository.BlogRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BlogCharacterAt @Inject constructor(
    private val blogRepository: BlogRepository
) : ObservableUseCase<String, BlogCharacterAt.Params?>() {

    override fun buildUseCaseObservable(params: Params?): Observable<String> {
        if (params == null) return Observable.error(IllegalArgumentException("Params character index can't be null!"))
        return blogRepository.getBlog()
            .flatMap {
                Observable.just(
                    if (it.length > params.index)
                        it[params.index].toString()
                    else ""
                )
            }.subscribeOn(Schedulers.io())
    }

    data class Params constructor(val index: Int) {
        companion object {
            fun characterAt(index: Int): Params {
                return Params(index)
            }
        }
    }
}
