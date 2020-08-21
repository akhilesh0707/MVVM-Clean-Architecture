package com.aqube.truecaller.data.source

import com.aqube.truecaller.data.repository.BlogDataSource
import javax.inject.Inject

open class BlogDataSourceFactory @Inject constructor(
    private val blogRemoteDataSource: BlogRemoteDataSource
) {
    open fun getDataSource(blogCached: Boolean): BlogDataSource {
        return blogRemoteDataSource
    }
}
