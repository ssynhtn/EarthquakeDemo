package com.ssynhtn.earthquake

import com.google.gson.Gson
import com.ssynhtn.earthquake.model.ui.Earthquake
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testGson() {
        val text = "{}"
        val quake = Gson().fromJson(text, Earthquake::class.java)
        println(quake)
        assertEquals(0, quake.time)
        assertEquals("", quake.magnitude)

    }
}