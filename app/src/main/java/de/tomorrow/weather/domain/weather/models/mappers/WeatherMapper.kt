package de.tomorrow.weather.domain.weather.models.mappers

import de.tomorrow.weather.data.models.WeatherDataDto
import de.tomorrow.weather.domain.weather.models.WeatherData

fun WeatherDataDto.toDomainObject(name: String): WeatherData {
    return WeatherData(
        name = name,
        latitude = this.latitude,
        longitude = this.longitude,
        elevation = this.elevation,
        temperature = this.currentWeather.temperature,
        windSpeed = this.currentWeather.windSpeed,
        windDirection = this.currentWeather.windDirection
    )
}