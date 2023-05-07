package de.tomorrow.weather.data.models

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDto(
    @SerializedName("time") val time: String,
    @SerializedName("temperature") val temperature: Double,
    @SerializedName("windspeed") val windSpeed: Double,
    @SerializedName("winddirection") val windDirection: Int
)