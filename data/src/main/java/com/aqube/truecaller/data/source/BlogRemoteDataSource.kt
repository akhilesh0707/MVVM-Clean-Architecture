package com.aqube.truecaller.data.source

import com.aqube.truecaller.data.repository.BlogDataSource
import com.aqube.truecaller.data.repository.BlogRemote
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class BlogRemoteDataSource @Inject constructor(
    private val blogRemote: BlogRemote
) : BlogDataSource {

    override fun getBlogs(): Observable<String> {
        return blogRemote.getBlogs()
    }
}