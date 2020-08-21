package com.aqube.truecaller.domain.interector.blogs

import com.aqube.truecaller.domain.executor.PostExecutionThread
import com.aqube.truecaller.domain.interector.ObservableUseCase
import com.aqube.truecaller.domain.repository.BlogRepository
import io.reactivex.Observable
import javax.inject.Inject

class Blog10thCharacter @Inject constructor(
    private val blogRepository: BlogRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<String, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<String> {
        return blogRepository.getBlogs()
            .flatMap {
                Observable.just(it[10].toString())
            }
    }
}
