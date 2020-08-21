package com.aqube.truecaller.remote

import com.aqube.truecaller.data.repository.BlogRemote
import com.aqube.truecaller.remote.service.BlogService
import io.reactivex.Flowable
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
    private val service: BlogService,
) : BlogRemote {

    override fun getBlogs(): Flowable<String> {
        return service.getBlogResponse()
    }
}
