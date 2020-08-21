package com.aqube.truecaller.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aqube.truecaller.domain.interector.blogs.GetBlog
import com.aqube.truecaller.presentation.state.Resource
import com.aqube.truecaller.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BlogViewModel @Inject constructor(
    private val getBlog: GetBlog
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<String>> = MutableLiveData()

    override fun onCleared() {
        getBlog.dispose()
        super.onCleared()
    }

    fun getBlogs(): LiveData<Resource<String>> {
        return liveData
    }

    fun fetchBlog() {
        liveData.postValue(Resource(ResourceState.LOADING))
        getBlog.execute(ProjectsSubscriber())
    }

    inner class ProjectsSubscriber : DisposableObserver<String>() {
        override fun onComplete() {}

        override fun onNext(t: String) {
            liveData.postValue(Resource(ResourceState.SUCCESS, t))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, message = e.localizedMessage))
        }
    }

}