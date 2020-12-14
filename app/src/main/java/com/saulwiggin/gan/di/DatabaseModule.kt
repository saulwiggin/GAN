package com.saulwiggin.gan.di

import android.app.Application
import androidx.room.Room
import com.saulwiggin.gan.database.BreakingBadDao
import com.saulwiggin.gan.database.BreakingBadCharacterDatabase

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): BreakingBadCharacterDatabase {
        return Room.databaseBuilder(application, BreakingBadCharacterDatabase::class.java, "breakingbad.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    fun provideDao(database: BreakingBadCharacterDatabase): BreakingBadDao {
        return database.Dao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}