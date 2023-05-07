@file:OptIn(FlowPreview::class)

package de.tomorrow.weather.domain.weather.usecases

import de.tomorrow.weather.data.repositories.WeatherRepository
import de.tomorrow.weather.data.sources.LocationRepository
import de.tomorrow.weather.domain.weather.models.WeatherData
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class WeatherUseCasesImpl(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
) : WeatherUseCases {

    private var currentItem: Int = 0

    private var _weatherLoop: Flow<WeatherData> = tickerFlow((currentItem * 10).seconds)
        .flatMapConcat { locationRepository.getAvailableLocations() }
        .onEach { if (currentItem >= it.size) currentItem = 0 }
        .map { it[currentItem] }
        .flatMapConcat { weatherRepository.getWeather(it.name, it.latitude, it.longitude) }
        .onEach { currentItem++ }

    private fun tickerFlow(initialDelay: Duration = Duration.ZERO) = flow {
        delay(initialDelay)
        while (true) {
            emit(Unit)
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC STREAMS
    ///////////////////////////////////////////////////////////////////////////

    override val weatherLoop = _weatherLoop
}