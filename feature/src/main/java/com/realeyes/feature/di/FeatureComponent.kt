package com.realeyes.feature.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.realeyes.core.Constants
import com.realeyes.domain.usecase.GetVideosUseCase
import com.realeyes.feature.videos_list.data.VideosApi
import com.realeyes.feature.videos_list.data.VideosDataSourceImp
import com.realeyes.feature.videos_list.data.VideosRepoImp
import com.realeyes.feature.videos_list.presentation.VideosAdapter
import com.realeyes.feature.videos_list.presentation.VideosViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val featureUiModule = module {
    factory { VideosAdapter() }
}

val viewModelModule = module {
    viewModel {
        VideosViewModel(
            get()
        )
    }

}

val useCaseModule = module {
    factory { GetVideosUseCase(get<VideosRepoImp>()) }
}

val featureDataModule = module {
    single { provideRetrofit() }
    single { get<Retrofit>().create(VideosApi::class.java) }

    factory { VideosRepoImp(get()) }
    factory { VideosDataSourceImp(get()) }


}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}





