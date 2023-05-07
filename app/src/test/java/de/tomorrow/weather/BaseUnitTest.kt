@file:OptIn(ExperimentalCoroutinesApi::class)

package de.tomorrow.weather

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

abstract class BaseUnitTest {

    protected val coroutinesManager: TestCoroutinesManager =
        TestCoroutinesManager(StandardTestDispatcher())

    @Before
    open fun setUp() {
        Dispatchers.setMain(coroutinesManager.main)
    }

    @After
    open fun tearUp() {
        Dispatchers.resetMain()
    }
}