package de.tomorrow.weather.presentation.screens.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.tomorrow.weather.core.ScreenState
import de.tomorrow.weather.domain.weather.models.WeatherData
import de.tomorrow.weather.domain.weather.usecases.WeatherUseCases
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val weatherUseCases: WeatherUseCases
) : ViewModel() {
    private var _screenState: MutableStateFlow<ScreenState<WeatherData>> =
        MutableStateFlow(ScreenState.Loading)

    init {
        requestData()
    }

    private fun requestData() {
        weatherUseCases.weatherFlow
            .onEach {
                _screenState.value = ScreenState.Content(it)
            }
            .catch {
                _screenState.value = ScreenState.Error(it)
            }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(1), replay = 1)
            .launchIn(viewModelScope)
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC
    ///////////////////////////////////////////////////////////////////////////

    val screenState = _screenState.asStateFlow()
}