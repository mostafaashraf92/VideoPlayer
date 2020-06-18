package com.realeyes.videoplayer.ui.main

import android.app.Application
import com.realeyes.feature.featureDataModule
import com.realeyes.feature.featureUiModule
import com.realeyes.feature.useCaseModule
import com.realeyes.feature.viewModelModule
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
                    featureUiModule,
                    featureDataModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}