package com.miphorez.taskapplication.di

import com.miphorez.taskapplication.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { MainActivityViewModel(get(), get()) }
}