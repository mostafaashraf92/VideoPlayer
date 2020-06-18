package com.realeyes.core.di

import com.realeyes.core.ApiPaths
import com.realeyes.core.Constants
import org.koin.dsl.module

val coreModules = module {
    single { ApiPaths() }
    single { Constants() }
    single { Constants() }
}

