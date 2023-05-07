package de.tomorrow.weather.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.tomorrow.weather.utils.coroutines.CoroutinesManager
import de.tomorrow.weather.utils.coroutines.RealCoroutinesManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UtilsModule {
    @Binds
    @Singleton
    fun bindCoroutinesManager(realCoroutinesManager: RealCoroutinesManager): CoroutinesManager
}