package de.tomorrow.weather.domain.weather.usecases

import de.tomorrow.weather.domain.weather.models.WeatherData
import kotlinx.coroutines.flow.Flow

interface WeatherUseCases {
    val weatherFlow: Flow<WeatherData>
    companion object {
        const val WEATHER_INTERVAL_SECONDS = 10
    }
}