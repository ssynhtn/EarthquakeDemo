package com.ssynhtn.earthquake

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ssynhtn.earthquake.model.ui.Coordinate
import com.ssynhtn.earthquake.model.ui.Earthquake
import com.ssynhtn.earthquake.repo.EarthquakeRepo
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.lang.RuntimeException
import kotlin.random.Random

@RunWith(RobolectricTestRunner::class)
class EarthquakeViewModelTest {

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private val rand = Random(System.currentTimeMillis())

    @Test
    fun testFetch() {
        val data = mutableListOf<Earthquake>()
        for (i in 0 until 10) {
            data.add(randomEarthquake())
        }
        val viewModel = EarthquakeListViewModel(FakeRepo(data))

        viewModel.refresh(coroutineRule.testDispatcher)
        assertEquals(data, viewModel.items.value?.getOrNull())
    }

    @Test
    fun testFetchFailure() {
        val ex = RuntimeException()
        val viewModel = EarthquakeListViewModel(FakeRepo(ex))

        viewModel.refresh(coroutineRule.testDispatcher)
        assertEquals(ex, viewModel.items.value?.exceptionOrNull())
    }

    private fun randomEarthquake(): Earthquake {
        return Earthquake(
            rand.nextLong().toString(),
            rand.nextLong(),
            rand.nextLong().toString(),
            rand.nextDouble(),
            Coordinate(
                rand.nextDouble(), rand.nextDouble(), rand.nextDouble()
            )
        )
    }
}

class FakeRepo(private val data: List<Earthquake>) : EarthquakeRepo {
    var ex: Exception? = null
    constructor(ex: Exception) : this(emptyList()) {
        this.ex = ex
    }
    override suspend fun fetch(): List<Earthquake> {
        Thread.sleep(1000)
        ex?.let { throw it }
        return data
    }

}


