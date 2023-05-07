package de.tomorrow.weather.data.repositories

import de.tomorrow.weather.data.sources.WeatherDataSource
import de.tomorrow.weather.domain.repositories.WeatherRepository
import de.tomorrow.weather.domain.weather.models.WeatherData
import de.tomorrow.weather.domain.weather.models.mappers.toDomainObject
import de.tomorrow.weather.utils.coroutines.CoroutinesManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherDataSource,
    private val coroutinesManager: CoroutinesManager
) : WeatherRepository {
    override fun getWeather(name: String, lat: Float, lon: Float): Flow<WeatherData> {
        return remoteDataSource.getWeather(lat, lon)
            .map { it.toDomainObject(name) }
            .flowOn(coroutinesManager.io)
    }
}