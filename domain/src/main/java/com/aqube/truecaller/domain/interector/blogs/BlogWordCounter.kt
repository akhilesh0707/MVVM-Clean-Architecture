package com.aqube.truecaller.domain.interector.blogs

import com.aqube.truecaller.domain.interector.ObservableUseCase
import com.aqube.truecaller.domain.repository.BlogRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BlogWordCounter @Inject constructor(
    private val blogRepository: BlogRepository
) : ObservableUseCase<String, Nothing?>() {

    override fun buildUseCaseObservable(params: Nothing?): Observable<String> {
        return blogRepository.getBlog()
            .map {
                val map = mutableMapOf<String, Int>()
                it.split(" ").map { key ->
                    val count = map.getOrElse(key, { 0 })
                    map[key] = count + 1
                }
                map
            }
            .map {
                val stringBuilder = StringBuilder()
                it.forEach { map ->
                    stringBuilder.append(map.key.replace("\n", "") + " = \t" + map.value + "\n")
                }
                stringBuilder.toString()
            }.subscribeOn(Schedulers.io())
    }
}