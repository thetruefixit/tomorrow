package de.tomorrow.weather.utils.coroutines

import kotlin.coroutines.CoroutineContext

interface CoroutinesManager {
    val main: CoroutineContext
    val io: CoroutineContext
}