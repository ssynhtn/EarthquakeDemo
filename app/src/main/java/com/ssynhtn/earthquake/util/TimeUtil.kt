package com.ssynhtn.earthquake.util

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    private val simpleDataFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun formatDate(time: Long): String {
        return simpleDataFormat.format(Date(time))
    }
}