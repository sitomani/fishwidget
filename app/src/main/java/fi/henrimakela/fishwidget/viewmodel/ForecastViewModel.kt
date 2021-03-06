package fi.henrimakela.fishwidget.viewmodel

import WeatherResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.henrimakela.data.repository.WeatherRepository
import fi.henrimakela.domain.Resource
import fi.henrimakela.domain.Status
import fi.henrimakela.domain.fish.FishForecast
import fi.henrimakela.fishwidget.data.WeatherDataSourceImpl
import fi.henrimakela.fishwidget.data.network.WeatherService
import fi.henrimakela.usecases.GetFishForecast
import fi.henrimakela.usecases.GetWeather
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ForecastViewModel : ViewModel(), KoinComponent {

    private val getWeather: GetWeather by inject()
    private val getFishForecast: GetFishForecast by inject()

    private var _weatherResponse = MutableLiveData<Resource<WeatherResponse>>()
    private var _fishForecast = MutableLiveData<FishForecast>()

    private var _isLoading = MutableLiveData<Boolean>()

    val weatherResponse: LiveData<Resource<WeatherResponse>> get() = _weatherResponse
    val fishForecast: LiveData<FishForecast> get() = _fishForecast

    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getForecastWithCoordinates(lat: Double, lon: Double) {
        _isLoading.value = true

        viewModelScope.launch {

            val weather =    getWeather(lat, lon)

            if(weather.status == Status.SUCCESS){
                weather.data?.let {
                    _fishForecast.postValue(getFishForecast(it))
                }

            }
            _isLoading.value = false
        }
    }
}