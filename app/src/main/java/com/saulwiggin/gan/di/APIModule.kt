package com.saulwiggin.gan.di

import com.saulwiggin.gan.network.BadAPIService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): BadAPIService {
        return retrofit.create(BadAPIService::class.java)
    }

    single { provideUserApi(get()) }
}