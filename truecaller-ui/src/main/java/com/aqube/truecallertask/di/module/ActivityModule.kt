package com.aqube.truecallertask.di.module

import com.aqube.truecaller.domain.executor.PostExecutionThread
import com.aqube.truecallertask.blogs.MainActivity
import com.aqube.truecallertask.UIThread
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}
