package com.aqube.truecallertask.injection.module

import com.aqube.truecaller.data.repository.BlogRemote
import com.aqube.truecaller.remote.BlogRemoteImpl
import com.aqube.truecaller.remote.service.BlogService
import com.aqube.truecaller.remote.service.ServiceFactory
import com.aqube.truecallertask.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideBlogService(): BlogService {
        return ServiceFactory.makeBlogService(BuildConfig.DEBUG)
    }

    @Provides
    fun bindBlogRemote(blogRemote: BlogRemoteImpl): BlogRemote = blogRemote

}
