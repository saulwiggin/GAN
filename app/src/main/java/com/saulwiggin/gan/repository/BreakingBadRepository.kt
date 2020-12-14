package com.saulwiggin.gan.repository

import androidx.lifecycle.LiveData
import com.saulwiggin.gan.database.BreakingBadCharacter
import com.saulwiggin.gan.database.BreakingBadCharacterDatabase
import com.saulwiggin.gan.network.BadAPIService
import com.saulwiggin.gan.network.RetrofitInstance

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class BreakingBadRepository(private val apiService: BadAPIService, private val database: BreakingBadCharacterDatabase) {

    suspend fun refreshCharacters() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh characters is called");
            val characterList = apiService.getCharacterList().await()
            database.Dao.insertAll(characterList)
        }
    }

    fun getItemsForSearch(filter: String): LiveData<List<BreakingBadCharacter>> {
        return database.Dao.getItemsSearch(filter)
    }

    suspend fun getBreakingBadAPI() =
        RetrofitInstance.api.getCharacterList()
}