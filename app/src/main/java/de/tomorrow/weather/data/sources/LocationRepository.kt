package de.tomorrow.weather.data.sources

import de.tomorrow.weather.domain.weather.models.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getAvailableLocations(): Flow<List<Location>>
}