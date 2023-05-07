@file:OptIn(ExperimentalCoroutinesApi::class)

package de.tomorrow.weather

import de.tomorrow.weather.utils.coroutines.CoroutinesManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher

class TestCoroutinesManager(testDispatcher: TestDispatcher) : CoroutinesManager {
    override val main by lazy { testDispatcher }
    override val io by lazy { testDispatcher }
}