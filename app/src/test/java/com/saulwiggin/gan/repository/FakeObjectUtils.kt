package com.saulwiggin.gan.repository

import com.saulwiggin.gan.database.BreakingBadCharacter

object FakeObjectsUtils{
    val DBDatabaseCharacter = BreakingBadCharacter(char_id = 1,  name = "Skyler White" , nickname = "Sky" , img = "https://www.globalcosmeticsnews.com/wp-content/uploads/2020/04/Google.png", status = "visible", occupation = listOf()
        , appearance = listOf("1","2","3"))

    val listDBDatabaseCharacter = listOf(DBDatabaseCharacter)
}