package de.tomorrow.weather.data.repositories

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.tomorrow.weather.R
import de.tomorrow.weather.domain.repositories.LocationRepository
import de.tomorrow.weather.domain.weather.models.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocationRepositoryImpl(private val context: Context) : LocationRepository {

    override fun getAvailableLocations(): Flow<List<Location>> = flow {
        val inputJson = context.resources.openRawResource(R.raw.locations).bufferedReader()
            .use { it.readText() }
        val typeToken = object : TypeToken<List<Location>>() {}.type
        val locations: List<Location> = Gson().fromJson(inputJson, typeToken)
        emit(locations)
    }

}