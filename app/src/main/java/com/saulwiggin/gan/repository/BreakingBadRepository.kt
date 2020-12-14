package com.saulwiggin.gan.repository


import androidx.lifecycle.LiveData
import com.saulwiggin.gan.database.CharacterDatabase
import com.saulwiggin.gan.database.BreakingBadCharacter
import com.saulwiggin.gan.network.BadAPIService
import com.saulwiggin.gan.utils.OpenForTesting
import com.saulwiggin.gan.utils.networkutils.LoadingState


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


@OpenForTesting
class CharactersRepository(private val breakingbadApiservices: BadAPIService, private val database: CharacterDatabase) {
    suspend fun refreshCharacters() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh characters is called");
            val characterList = breakingbadApiservices.getCharacterList().await()
            database.characterDao.insertAll(characterList)
        }
    }

    fun getItemsSearch(filter: String): LiveData<List<BreakingBadCharacter>> {
        return database.characterDao.getItemsSearch(filter)
    }

    fun getItemsByAppearance(season: String): LiveData<List<BreakingBadCharacter>> {
        return database.characterDao.getLocalCharactersByAppearance(season)
    }
}