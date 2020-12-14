package com.saulwiggin.gan.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.saulwiggin.gan.database.BreakingBadCharacter
import com.saulwiggin.gan.repository.BreakingBadRepository
import com.saulwiggin.gan.util.Resource
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Response

class ListViewModel( val respository: BreakingBadRepository): ViewModel() {

    private val TAG ="Breaking Bad Repo:"

    val breakingBad: MutableLiveData<Resource<BreakingBadCharacter>> = MutableLiveData()
    var breakingBadPage = 1
    var breakingBadResponse: BreakingBadCharacter? = null

    private val _state = MutableLiveData<Resource<String>>()
    private val state: LiveData<Resource<String>>
    get() = _state

    init {
        getBreakingBad()
    }

    fun getBreakingBad() = viewModelScope.launch {
        try {
            _state.value = Resource.Loading()
            breakingBad.postValue(Resource.Loading())
            val response = respository.getBreakingBadAPI()
        } catch ( ex: Exception){
            Log.v(TAG, ex.toString())
        }

    }

    private fun handleBreakingBad(response: Response<BreakingBadCharacter>): Resource<BreakingBadCharacter> {
        if(response.isSuccessful){
            response.body()?.let {
                    resultResponse ->
                breakingBadPage++
                if(breakingBadResponse == null){
                    breakingBadResponse = resultResponse
                } else {
                    val oldArticles = breakingBadResponse?.name
                    val newArticles = resultResponse.name
                }
                return Resource.Success(breakingBadResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private val _navigateToSelectedProperty = MutableLiveData<BreakingBadCharacter>()
    val navigateToSelectedProperty: LiveData<BreakingBadCharacter>
        get() = _navigateToSelectedProperty

    fun displayPropertyDetails(characterProperty: BreakingBadCharacter) {
        _navigateToSelectedProperty.value = characterProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    lateinit var allItemsSearch: LiveData<List<BreakingBadCharacter>>
    var search = MutableLiveData<String>("%")

    init {
        allItemsSearch = Transformations.switchMap(search) { search ->
            respository.getItemsForSearch(search)

        }
    }

    fun setSearch(newSearch: String) {
        val f = when {
            newSearch.isEmpty() -> "%"
            else -> "%$newSearch%"
        }
        search.postValue(f)
    }

}