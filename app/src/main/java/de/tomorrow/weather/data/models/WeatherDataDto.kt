package de.tomorrow.weather.data.models

import com.google.gson.annotations.SerializedName

data class WeatherDataDto(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("elevation") val elevation: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("current_weather") val currentWeather: CurrentWeatherDto
)