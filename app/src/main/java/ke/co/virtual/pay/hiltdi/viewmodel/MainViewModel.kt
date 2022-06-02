package ke.co.virtual.pay.hiltdi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.virtual.pay.hiltdi.model.Country
import ke.co.virtual.pay.hiltdi.repository.Repository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val countryLiveData = MutableLiveData<List<Country>?>()

    fun getCountry() = countryLiveData

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            val countries = repository.getCountries()

            when (countries.isSuccessful) {
                true -> {
                    with(countries.body().orEmpty()) {
                        var countryList = listOf<Country>()
                        forEach { (_, _, _, _, _, _, capital, _, _, _, _, _, _, _, name) ->
                            countryList = countryList + Country(name, capital)
                        }
                        countryLiveData.postValue(countryList)
                    }
                }
                else -> {
                    Timber.e(countries.message())
                }
            }
        }
    }
}