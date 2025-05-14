package com.realeyes.common_ui.navigation.di

import com.realeyes.common_ui.navigation.Navigator
import org.koin.dsl.module

val uiModule = module {
    single {
        Navigator()
    }
}




