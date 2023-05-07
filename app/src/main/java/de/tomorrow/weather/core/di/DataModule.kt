package de.tomorrow.weather.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.tomorrow.weather.data.network.WeatherApi
import de.tomorrow.weather.data.repositories.WeatherRepository
import de.tomorrow.weather.data.repositories.WeatherRepositoryImpl
import de.tomorrow.weather.data.sources.LocationRepository
import de.tomorrow.weather.data.sources.LocationRepositoryImpl
import de.tomorrow.weather.data.sources.WeatherDataSource
import de.tomorrow.weather.data.sources.WeatherDataSourceImpl
import de.tomorrow.weather.utils.coroutines.CoroutinesManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideWeatherDataSource(weatherApi: WeatherApi): WeatherDataSource =
        WeatherDataSourceImpl(weatherApi, "Europe/Berlin")

    @Provides
    @Singleton
    fun provideWeatherRepository(
        remoteDataSource: WeatherDataSource,
        coroutinesManager: CoroutinesManager
    ): WeatherRepository =
        WeatherRepositoryImpl(remoteDataSource, coroutinesManager)

    @Provides
    @Singleton
    fun provideLocationsRepository(@ApplicationContext context: Context): LocationRepository =
        LocationRepositoryImpl(context)
}