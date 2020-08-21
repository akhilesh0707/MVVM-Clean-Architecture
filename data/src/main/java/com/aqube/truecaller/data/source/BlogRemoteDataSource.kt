package com.aqube.truecaller.data.source

import com.aqube.truecaller.data.repository.BlogDataSource
import com.aqube.truecaller.data.repository.BlogRemote
import io.reactivex.Flowable
import javax.inject.Inject

class BlogRemoteDataSource @Inject constructor(
    private val blogRemote: BlogRemote
) : BlogDataSource {

    override fun getBlogs(): Flowable<String> {
        return blogRemote.getBlogs()
    }
}