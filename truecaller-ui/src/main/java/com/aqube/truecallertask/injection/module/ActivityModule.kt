package com.aqube.truecallertask.injection.module

import com.aqube.truecaller.domain.executor.PostExecutionThread
import com.aqube.truecallertask.ui.MainActivity
import com.aqube.truecallertask.base.UIThread
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
