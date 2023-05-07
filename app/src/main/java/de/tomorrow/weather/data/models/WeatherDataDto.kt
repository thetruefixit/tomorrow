package de.tomorrow.weather.data.models

import com.google.gson.annotations.SerializedName

data class WeatherDataDto(
    @SerializedName("latitude") val latitude: Float,
    @SerializedName("longitude") val longitude: Float,
    @SerializedName("elevation") val elevation: Float,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("current_weather") val currentWeather: CurrentWeatherDto
)