package com.saulwiggin.gan.di

import com.saulwiggin.gan.viewmodel.DetailViewModel
import com.saulwiggin.gan.viewmodel.ListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DetailViewModel(get()) }

    viewModel { ListViewModel(get()) }
}