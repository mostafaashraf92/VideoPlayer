package com.realeyes.videoplayer.ui.main

import android.app.Application
import com.realeyes.data.repositories.di.networkModule
import com.realeyes.feature.di.uiModule
import com.realeyes.feature.di.useCaseModule
import com.realeyes.feature.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            androidFileProperties()
            modules(
                listOf(
                    uiModule,
                    networkModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}