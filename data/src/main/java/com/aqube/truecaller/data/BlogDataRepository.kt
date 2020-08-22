package com.aqube.truecaller.data

import com.aqube.truecaller.domain.repository.BlogRepository
import com.aqube.truecaller.data.source.BlogDataSourceFactory
import io.reactivex.Observable
import javax.inject.Inject

class BlogDataRepository @Inject constructor(
    private val factory: BlogDataSourceFactory
) : BlogRepository {

    override fun getBlog(): Observable<String> {
        return factory.getDataSource().getBlog()
    }
}
