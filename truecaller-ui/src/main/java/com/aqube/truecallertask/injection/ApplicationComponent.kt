package com.aqube.truecallertask.injection

import android.app.Application
import com.aqube.truecallertask.base.TrueCallerApplication
import com.aqube.truecallertask.injection.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        PresentationModule::class,
        DataModule::class,
        RemoteModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: TrueCallerApplication)
}