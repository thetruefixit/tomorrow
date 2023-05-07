package de.tomorrow.weather.domain.weather.usecases

import de.tomorrow.weather.domain.weather.models.WeatherData
import kotlinx.coroutines.flow.Flow

interface WeatherUseCases {
    val weatherLoop: Flow<WeatherData>
}