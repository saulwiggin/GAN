package com.saulwiggin.gan.network

import kotlinx.coroutines.Deferred

interface BadAPIService {
    @GET(API_CHAR_LIST)
    fun getBreakingBadChars(): Deferred<list<BreakingBadCharacter>>
}