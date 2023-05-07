@file:OptIn(ExperimentalCoroutinesApi::class)

package de.tomorrow.weather.tests.sources

import app.cash.turbine.test
import de.tomorrow.weather.BaseUnitTest
import de.tomorrow.weather.data.models.CurrentWeatherDto
import de.tomorrow.weather.data.models.WeatherDataDto
import de.tomorrow.weather.data.network.WeatherApi
import de.tomorrow.weather.data.sources.WeatherDataSource
import de.tomorrow.weather.data.sources.WeatherDataSourceImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteWeatherDataSourceTest : BaseUnitTest() {

    @MockK
    lateinit var weatherApi: WeatherApi

    private lateinit var remoteDataSource: WeatherDataSource

    @Before
    override fun setUp() {
        super.setUp()
        MockKAnnotations.init(this)
        remoteDataSource = WeatherDataSourceImpl(weatherApi, TIMEZONE)
    }

    @Test
    fun testGetWeather() = runTest {
        val expectedLat = 1F
        val expectedLon = 1f
        val expectedResult = WeatherDataDto(
            expectedLat,
            expectedLon,
            1f,
            TIMEZONE,
            CurrentWeatherDto("", 10f, 10f, 100)
        )
        coEvery { weatherApi.getWeather(any(), any(), any(), any()) } returns Response.success(
            expectedResult
        )
        remoteDataSource.getWeather(1F, 1F).test {
            val actualItem = awaitItem()
            awaitComplete()
            assertEquals(expectedResult, actualItem)
            coVerify { weatherApi.getWeather(expectedLat, expectedLon, TIMEZONE, true) }
        }
    }

    @Test
    fun testGetWeatherWithError() = runTest {
        val expectedLat = 1F
        val expectedLon = 1f
        coEvery { weatherApi.getWeather(any(), any(), any(), any()) } returns Response.error(
            404,
            ResponseBody.create(MediaType.parse("application/json"), "")
        )
        remoteDataSource.getWeather(1F, 1F).test {
            awaitError()
            coVerify { weatherApi.getWeather(expectedLat, expectedLon, TIMEZONE, true) }
        }
    }

    companion object {
        const val TIMEZONE = "GMT"
    }
}