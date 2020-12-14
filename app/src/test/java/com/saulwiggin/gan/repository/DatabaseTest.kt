package com.saulwiggin.gan.repository


import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.saulwiggin.gan.database.BreakingBadCharacterDatabase
import com.saulwiggin.gan.database.BreakingBadDao
import org.junit.After
import org.junit.Before
import java.lang.Exception

open class DatabaseTest {
    // system under test
    protected lateinit var database: BreakingBadCharacterDatabase
    val characterDao: BreakingBadDao
        get() = database.Dao

    @Before
    open fun init() {
        //Create a temporally database
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BreakingBadCharacterDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun finish() {
        try {
            database.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}