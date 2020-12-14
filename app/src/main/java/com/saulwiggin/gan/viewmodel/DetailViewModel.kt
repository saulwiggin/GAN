package com.saulwiggin.gan.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulwiggin.gan.database.BreakingBadCharacter
import com.saulwiggin.gan.network.BadAPIService
import com.saulwiggin.gan.repository.BreakingBadRepository
import com.saulwiggin.gan.util.Resource
import kotlinx.coroutines.*

class DetailViewModel(private val breakingBadRepository: BreakingBadRepository) : ViewModel() {

    private val _state = MutableLiveData<Resource<String>>()

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    init {
        refreshDataFromRepository()
    }

    fun refreshDataFromRepository(){
        viewModelScope.launch {
            try {
                breakingBadRepository.refreshCharacters()
            } catch (ex: Exception){

            }
        }
    }

    override fun onCleared(){
        super.onCleared()
        viewModelJob.cancel()
    }

}