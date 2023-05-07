package de.tomorrow.weather.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.tomorrow.weather.domain.repositories.LocationRepository
import de.tomorrow.weather.domain.repositories.WeatherRepository
import de.tomorrow.weather.domain.weather.usecases.WeatherUseCases
import de.tomorrow.weather.domain.weather.usecases.WeatherUseCasesImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    @Singleton
    fun provideWeatherUseCases(
        locationRepository: LocationRepository,
        weatherRepository: WeatherRepository
    ): WeatherUseCases = WeatherUseCasesImpl(locationRepository, weatherRepository)
}