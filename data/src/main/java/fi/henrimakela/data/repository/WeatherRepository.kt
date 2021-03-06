package fi.henrimakela.data.repository

import fi.henrimakela.data.WeatherDataSource

class WeatherRepository(private val weatherDataSource: WeatherDataSource) {

    suspend fun getWeather(lat: Double, lon: Double) = weatherDataSource.getWeather(lat, lon)
}