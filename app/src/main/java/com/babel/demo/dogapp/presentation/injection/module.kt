package com.babel.demo.dogapp.presentation.injection

import com.babel.demo.dogapp.data.remote.API_BASE_URL
import com.babel.demo.dogapp.data.remote.DogApi
import com.babel.demo.dogapp.data.remote.createHttpClient
import com.babel.demo.dogapp.data.remote.createWebService
import com.babel.demo.dogapp.data.repository.DogRepositoryImpl
import com.babel.demo.dogapp.domain.repository.DogRepository
import com.babel.demo.dogapp.domain.usecases.GetDogsBaseUseCase
import com.babel.demo.dogapp.presentation.features.detail.DetailViewModel
import com.babel.demo.dogapp.presentation.features.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

val appModules = module {
    single {
        createWebService<DogApi>(
            okHttpClient = createHttpClient(),
            factory = RxJava2CallAdapterFactory.create(),
            baseUrl = API_BASE_URL
        )
    }
    factory<DogRepository> {
        DogRepositoryImpl(
            dogApi = get()
        )
    }
    factory {
        GetDogsBaseUseCase(
            dogRepository = get()
        )
    }
    viewModel {
        ListViewModel(
            getDogsUseCase = get()
        )
    }
    viewModel {
        DetailViewModel()
    }
}
