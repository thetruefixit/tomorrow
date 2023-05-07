package de.tomorrow.weather.data.sources

import de.tomorrow.weather.data.models.WeatherDataDto
import de.tomorrow.weather.data.network.WeatherApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherDataSourceImpl(
    private val weatherApi: WeatherApi,
    private val defaultTimeZone: String
) : WeatherDataSource {
    override fun getWeather(lat: Float, lon: Float): Flow<WeatherDataDto> {
        return flow {
            val result = weatherApi.getWeather(lat, lon, defaultTimeZone, true)
            val body = result.body()
            if (result.isSuccessful && result.code() == 200 && body != null) {
                emit(body)
            } else {
                throw Exception(result.message())
            }
        }
    }
}