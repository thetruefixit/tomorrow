package de.tomorrow.weather.domain.weather.models

data class WeatherData(
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val elevation: Float,
    val temperature: Float,
    val windSpeed: Float,
    val windDirection: Int
)