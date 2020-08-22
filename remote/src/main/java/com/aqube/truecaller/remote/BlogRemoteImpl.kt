package com.aqube.truecaller.remote

import com.aqube.truecaller.data.repository.BlogRemote
import com.aqube.truecaller.remote.service.BlogService
import io.reactivex.Observable
import javax.inject.Inject

class BlogRemoteImpl @Inject constructor(
    private val service: BlogService
) : BlogRemote {

    override fun getBlog(): Observable<String> {
        return service.getBlogResponse()
    }
}
