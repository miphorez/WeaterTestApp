package com.miphorez.taskapplication

import android.app.Application
import com.miphorez.taskapplication.di.networkModule
import com.miphorez.taskapplication.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TaskApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TaskApp)
            androidLogger()
            modules(listOf(viewmodelModule, networkModule))
        }
    }

}