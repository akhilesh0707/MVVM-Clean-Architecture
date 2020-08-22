package com.aqube.truecaller.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.aqube.truecaller.domain.interector.blogs.Blog10thCharacter
import com.aqube.truecaller.domain.interector.blogs.BlogEvery10thCharacter
import com.aqube.truecaller.domain.interector.blogs.BlogWordCounter
import com.aqube.truecaller.presentation.state.Resource
import com.aqube.truecaller.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import java.util.*
import javax.inject.Inject

class BlogViewModel @Inject constructor(
    private val blog10thCharacter: Blog10thCharacter,
    private val blogEvery10thCharacter: BlogEvery10thCharacter,
    private val blogWordCounter: BlogWordCounter
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<String>> = MutableLiveData()

    override fun onCleared() {
        blog10thCharacter.dispose()
        blogEvery10thCharacter.dispose()
        blogWordCounter.dispose()
        super.onCleared()
    }

    fun getBlogs(): LiveData<Resource<String>> {
        return liveData
    }

    fun fetchBlog() {
        liveData.postValue(Resource(ResourceState.LOADING))
        //blog10thCharacter.execute(BlogSubscriber())
        //blogEvery10thCharacter.execute(BlogSubscriber())
        blogWordCounter.execute(BlogSubscriber())
    }

    inner class BlogSubscriber : DisposableObserver<String>() {

        override fun onComplete() {}

        override fun onNext(t: String) {
            liveData.postValue(Resource(ResourceState.SUCCESS, t))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, message = e.localizedMessage))
        }
    }

}