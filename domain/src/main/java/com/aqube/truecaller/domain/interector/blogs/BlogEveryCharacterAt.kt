package com.aqube.truecaller.domain.interector.blogs

import com.aqube.truecaller.domain.interector.ObservableUseCase
import com.aqube.truecaller.domain.repository.BlogRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BlogEveryCharacterAt @Inject constructor(
    private val blogRepository: BlogRepository
) : ObservableUseCase<String, BlogEveryCharacterAt.Params?>() {

    override fun buildUseCaseObservable(params: Params?): Observable<String> {
        if (params == null) return Observable.error(IllegalArgumentException("Params character at index can't be null!"))
        return blogRepository.getBlogs()
            .map {
                val sb = StringBuilder()
                var counter = params.characterIndex - 1
                val charList = it.toList()
                while (charList.size > counter) {
                    sb.append(charList[counter])
                    counter += params.characterIndex
                }
                sb.toString()
            }.subscribeOn(Schedulers.io())
    }

    data class Params constructor(val characterIndex: Int) {
        companion object {
            fun everyCharacter(characterIndex: Int): Params {
                return Params(characterIndex)
            }
        }
    }
}