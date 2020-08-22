package com.aqube.truecaller.domain.interector.blogs

import com.aqube.truecaller.domain.interector.ObservableUseCase
import com.aqube.truecaller.domain.repository.BlogRepository
import io.reactivex.Observable
import javax.inject.Inject

class BlogEvery10thCharacter @Inject constructor(
    private val blogRepository: BlogRepository
) : ObservableUseCase<String, Nothing?>() {

    override fun buildUseCaseObservable(params: Nothing?): Observable<String> {
        return blogRepository.getBlogs()
            .map {
                val sb = StringBuilder()
                var counter = 9
                val charList = it.toList()
                while (charList.size > counter) {
                    sb.append(charList[counter])
                    counter += 10
                }
                sb.toString()
            }
    }
}