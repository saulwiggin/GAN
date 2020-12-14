package com.saulwiggin.gan.network

import com.saulwiggin.gan.database.BreakingBadCharacter
import com.saulwiggin.gan.network.API.API_CHARACTER_LIST
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface BadAPIService {
    @GET(API_CHARACTER_LIST)
    fun getCharacterList(): Deferred<List<BreakingBadCharacter>>
}