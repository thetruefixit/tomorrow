package de.tomorrow.weather.domain.weather.models

data class WeatherData(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val elevation: Double,
    val temperature: Double,
    val windSpeed: Double,
    val windDirection: Int
)