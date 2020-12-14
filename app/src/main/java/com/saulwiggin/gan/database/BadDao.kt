package com.saulwiggin.gan.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.saulwiggin.gan.util.Converters

@Dao
interface BreakingBadDao {
    @Query("select * from BreakingBad")
    fun getLocalDBCharacters(): LiveData<List<BreakingBadCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( characters: List<BreakingBadCharacter>)

    @Query("SELECT * from BreakingBad WHERE name LIKE :filter ")
    fun getItemsSearch(filter: String): LiveData<List<BreakingBadCharacter>>

}

@Database(entities = [BreakingBadCharacter::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BreakingBadCharacterDatabase: RoomDatabase() {
    abstract val Dao: BreakingBadDao
}