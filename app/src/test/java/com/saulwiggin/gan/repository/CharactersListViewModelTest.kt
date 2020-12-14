package com.saulwiggin.gan.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import com.saulwiggin.gan.repository.BreakingBadRepository
import com.saulwiggin.gan.util.Resource
import com.saulwiggin.gan.viewmodel.ListViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException

class CharactersListViewModelTest {

    @Mock
    private lateinit var mRepo: BreakingBadRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    lateinit var charactersListViewModel: ListViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this);
    }

}