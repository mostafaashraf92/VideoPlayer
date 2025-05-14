package com.realeyes.feature.di

import com.realeyes.common_ui.navigation.Navigator
import com.realeyes.domain.usecase.GetVideosUseCase
import com.realeyes.data.repositories.VideosRepoImp
import com.realeyes.feature.NavigationViewModel
import com.realeyes.feature.videos_list.presentation.VideosViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        VideosViewModel(
            get()
        )
    }
    viewModel {
        NavigationViewModel(
            get()
        )
    }

}

val uiModule = module {
    single {
        Navigator()
    }
}

val useCaseModule = module {
    factory { GetVideosUseCase(get<VideosRepoImp>()) }
}






