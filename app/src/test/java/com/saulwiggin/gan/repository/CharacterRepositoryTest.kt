package com.saulwiggin.gan.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.work.impl.utils.LiveDataUtils
import com.saulwiggin.gan.database.BreakingBadCharacter
import com.saulwiggin.gan.network.BadAPIService
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CharactersRepositoryTest : DatabaseTest() {

    private lateinit var mRepo: BreakingBadRepository

    @Mock
    lateinit var breakingbadApiservices: BadAPIService

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    override fun init() {
        super.init()
        MockitoAnnotations.initMocks(this);

        mRepo = BreakingBadRepository(
            breakingbadApiservices,
            database
        )
    }

    @Test
    fun test_login_repo_retrieves_expected_data() =
        runBlocking {
            val fakeToReturn =
                CompletableDeferred(FakeObjectsUtils.listDBDatabaseCharacter)

            Mockito.`when`(breakingbadApiservices.getCharacterList())
                .thenReturn(fakeToReturn)

            val dataReceived = mRepo.refreshCharacters()

            assertNotNull(dataReceived)
        }

    @Test
    fun test_getItemsSearch_retrieves_expected_data() = runBlocking {
        //prep
        val fakeObject = FakeObjectsUtils.listDBDatabaseCharacter
        database.Dao.insertAll(fakeObject)
        val liveDataUtils = LiveDataTestUtil<List<BreakingBadCharacter>>()

        //action
        val dataReceived = mRepo.getItemsForSearch(fakeObject[0].name)
        val dataReturned = liveDataUtils.getValue(dataReceived)?.get(0)

        //verify
        assertNotNull(dataReceived)
        assertNotNull(dataReturned)

        assertEquals(fakeObject?.get(0)?.name, dataReturned?.name)
        assertEquals(fakeObject?.get(0)?.char_id, dataReturned?.char_id)
        assertEquals(fakeObject?.get(0)?.appearance, dataReturned?.appearance)
        assertEquals(fakeObject?.get(0)?.nickname, dataReturned?.nickname)
        assertEquals(fakeObject?.get(0)?.img, dataReturned?.img)
        assertEquals(fakeObject?.get(0)?.occupation, dataReturned?.occupation)
        assertEquals(fakeObject?.get(0)?.status, dataReturned?.status)

    }

    @Test
    fun test_list_is_empty_when_filter__not_found_anything() {
        //prep
        val fakeObject = FakeObjectsUtils.listDBDatabaseCharacter
        val liveDataUtils = LiveDataTestUtil<List<BreakingBadCharacter>>()

        //action
        val dataReceived = mRepo.getItemsForSearch(fakeObject[0].name)
        val dataReturned = liveDataUtils.getValue(dataReceived)

        //verify
        assertNotNull(dataReceived)
        assertNotNull(dataReturned)
        assertEquals(0, dataReturned?.size)

    }


}