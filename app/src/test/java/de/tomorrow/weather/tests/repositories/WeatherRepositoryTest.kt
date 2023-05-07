@file:OptIn(ExperimentalCoroutinesApi::class)

package de.tomorrow.weather.tests.repositories

import app.cash.turbine.test
import de.tomorrow.weather.BaseUnitTest
import de.tomorrow.weather.data.models.CurrentWeatherDto
import de.tomorrow.weather.data.models.WeatherDataDto
import de.tomorrow.weather.data.repositories.WeatherRepositoryImpl
import de.tomorrow.weather.data.sources.WeatherDataSource
import de.tomorrow.weather.domain.repositories.WeatherRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WeatherRepositoryTest : BaseUnitTest() {

    @MockK
    lateinit var remoteDataSource: WeatherDataSource

    private lateinit var repository: WeatherRepository

    @Before
    override fun setUp() {
        super.setUp()
        MockKAnnotations.init(this)
        repository = WeatherRepositoryImpl(remoteDataSource, coroutinesManager)
    }

    @After
    override fun tearUp() {
        super.tearUp()
    }

    @Test
    fun testGetWeather() = runTest {
        val expectedName = "Test"
        val expectedLat = 1F
        val expectedLon = 1f
        val expectedResult = WeatherDataDto(
            expectedLat,
            expectedLon,
            1f,
            TIMEZONE,
            CurrentWeatherDto("", 10f, 10f, 100)
        )
        coEvery { remoteDataSource.getWeather(any(), any()) } returns flowOf(expectedResult)

        repository.getWeather(expectedName, 1F, 1F).test {
            val actualItem = awaitItem()
            awaitComplete()
            assertEquals(expectedName, actualItem.name)
            assertEquals(expectedResult.elevation, actualItem.elevation)
            assertEquals(expectedResult.latitude, actualItem.latitude)
            assertEquals(expectedResult.longitude, actualItem.longitude)
            assertEquals(expectedResult.currentWeather.temperature, actualItem.temperature)
            assertEquals(expectedResult.currentWeather.windDirection, actualItem.windDirection)
            assertEquals(expectedResult.currentWeather.windSpeed, actualItem.windSpeed)
            coVerify { remoteDataSource.getWeather(expectedLat, expectedLon) }
        }
    }

    @Test
    fun testGetWeatherWithError() = runTest {
        val expectedLat = 1F
        val expectedLon = 1f

        coEvery {
            remoteDataSource.getWeather(
                any(),
                any()
            )
        } returns flow { throw Exception("Test exception") }

        remoteDataSource.getWeather(1F, 1F).test {
            awaitError()
            coVerify { remoteDataSource.getWeather(expectedLat, expectedLon) }
        }
    }

    companion object {
        const val TIMEZONE = "GMT"
    }
}