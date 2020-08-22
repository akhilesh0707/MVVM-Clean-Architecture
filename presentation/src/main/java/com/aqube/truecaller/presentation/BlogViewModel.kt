package com.aqube.truecaller.presentation

import androidx.lifecycle.LiveData
import com.aqube.truecaller.domain.interector.blogs.BlogCharacterAt
import com.aqube.truecaller.domain.interector.blogs.BlogEveryCharacterAt
import com.aqube.truecaller.domain.interector.blogs.BlogWordCounter
import com.aqube.truecaller.presentation.extension.addTo
import com.aqube.truecaller.presentation.extension.runOnBackground
import com.aqube.truecaller.presentation.state.TransientAwareConsumerLiveData
import com.aqube.truecaller.presentation.state.TransientAwareUiModel
import timber.log.Timber
import javax.inject.Inject

sealed class BlogUIModel : TransientAwareUiModel() {
    object Loading : BlogUIModel()
    data class Error(var error: String = "") : BlogUIModel()
    data class TenthCharacter(val tenthCharacter: String) : BlogUIModel()
    data class EveryTenthCharacter(val everyTenthCharacter: String) : BlogUIModel()
    data class WordCount(val wordCount: String) : BlogUIModel()
}


class BlogViewModel @Inject constructor(
    private val blogCharacterAt: BlogCharacterAt,
    private val blogEveryCharacterAt: BlogEveryCharacterAt,
    private val blogWordCounter: BlogWordCounter
) : BaseViewModel() {

    private val _planetUIModel = TransientAwareConsumerLiveData<BlogUIModel>()
    private var planetUIModel: LiveData<BlogUIModel> = _planetUIModel
    private val charIndex = 10

    fun getBlog(): LiveData<BlogUIModel> {
        return planetUIModel
    }

    fun fetchBlog() {
        blogCharacterAt.execute(BlogCharacterAt.Params.characterAt(charIndex))
            .runOnBackground(schedulerProvider)
            .map { BlogUIModel.TenthCharacter(it) as BlogUIModel }
            .startWith(BlogUIModel.Loading)
            .doOnError { Timber.e(it) }
            .onErrorReturn { BlogUIModel.Error(it.message ?: "") }
            .subscribe(_planetUIModel)
            .addTo(disposable)

        blogEveryCharacterAt.execute(BlogEveryCharacterAt.Params.everyCharacter(charIndex))
            .runOnBackground(schedulerProvider)
            .map { BlogUIModel.EveryTenthCharacter(it) as BlogUIModel }
            .startWith(BlogUIModel.Loading)
            .doOnError { Timber.e(it) }
            .onErrorReturn { BlogUIModel.Error(it.message ?: "") }
            .subscribe(_planetUIModel)
            .addTo(disposable)

        blogWordCounter.execute()
            .runOnBackground(schedulerProvider)
            .map { BlogUIModel.WordCount(it) as BlogUIModel }
            .startWith(BlogUIModel.Loading)
            .doOnError { Timber.e(it) }
            .onErrorReturn { BlogUIModel.Error(it.message ?: "") }
            .subscribe(_planetUIModel)
            .addTo(disposable)
    }

}