package com.ssynhtn.earthquake

import com.ssynhtn.earthquake.model.Coordinate
import com.ssynhtn.earthquake.model.Earthquake
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class EarthquakeRepo {
    suspend fun fetch(): List<Earthquake> {
        delay(3000)
        val data = arrayListOf<Earthquake>()
        for (i in 0 until 15) {
            data.add(randomItem())
        }
        return data
    }

    private fun randomItem(): Earthquake {
        return Earthquake(
            Math.random().toString(),
            (System.currentTimeMillis() + Math.random() * TimeUnit.DAYS.toMillis(365)).toLong(),
            PLACES[(Math.random() * PLACES.size).toInt()],
            (Math.random() * 10).toString(),
            Coordinate(
                Math.random() * 180,
                Math.random() * 90,
                Math.random() * 1000
            )
        )
    }

    companion object {
        val PLACES = arrayOf("Beijing", "USA", "Hangzhou")
    }
}