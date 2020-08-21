package com.aqube.truecallertask.injection.module

import com.aqube.truecaller.data.BlogDataRepository
import com.aqube.truecaller.domain.repository.BlogRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindBlogDataRepository(dataRepository: BlogDataRepository): BlogRepository
}
