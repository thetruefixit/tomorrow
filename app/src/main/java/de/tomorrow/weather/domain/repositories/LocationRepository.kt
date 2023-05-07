package de.tomorrow.weather.domain.repositories

import de.tomorrow.weather.domain.weather.models.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getAvailableLocations(): Flow<List<Location>>
}