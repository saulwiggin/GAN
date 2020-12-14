package com.saulwiggin.gan.di

import com.saulwiggin.gan.database.BreakingBadCharacterDatabase
import com.saulwiggin.gan.database.BreakingBadDao
import com.saulwiggin.gan.network.BadAPIService
import com.saulwiggin.gan.repository.BreakingBadRepository
import org.koin.dsl.module

val repositoryModule = module {
    fun provideCharacterRepository(api: BadAPIService, dao: BreakingBadCharacterDatabase): BreakingBadRepository {
        return BreakingBadRepository(api, dao)
    }
    single { provideCharacterRepository(get(), get()) }
}