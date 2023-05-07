package de.tomorrow.weather.data.models

import com.google.gson.annotations.SerializedName

data class CurrentWeatherDto(
    @SerializedName("time") val time: String,
    @SerializedName("temperature") val temperature: Float,
    @SerializedName("windspeed") val windSpeed: Float,
    @SerializedName("winddirection") val windDirection: Int
)