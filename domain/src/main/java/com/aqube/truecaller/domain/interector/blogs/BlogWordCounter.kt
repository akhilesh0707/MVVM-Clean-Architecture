package com.aqube.truecaller.domain.interector.blogs

import com.aqube.truecaller.domain.executor.PostExecutionThread
import com.aqube.truecaller.domain.interector.ObservableUseCase
import com.aqube.truecaller.domain.repository.BlogRepository
import io.reactivex.Observable
import javax.inject.Inject

class BlogWordCounter @Inject constructor(
    private val blogRepository: BlogRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<String, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<String> {
        return blogRepository.getBlogs()
            .map {
                val map = mutableMapOf<String, Int>()
                it.split(" ").map { key ->
                    val count = map.getOrElse(key, { 0 })
                    map[key] = count + 1
                }
                map
            }
            .map {
                val sb = StringBuilder()
                it.forEach {
                    sb.append(it.key.replace("\n","") + " = \t" + it.value + "\n")
                }
                sb.toString()
            }
    }
}