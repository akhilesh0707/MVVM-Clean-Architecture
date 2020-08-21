package com.aqube.truecallertask

import android.app.Activity
import android.app.Application
import com.aqube.truecallertask.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class TrueCallerApplication : Application(), HasActivityInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()

        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}

