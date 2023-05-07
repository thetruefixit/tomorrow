package de.tomorrow.weather.data.network

import de.tomorrow.weather.data.models.WeatherDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("timezone") timezone: String,
        @Query("current_weather") currentWeather: Boolean = true,
    ): Response<WeatherDataDto>
}