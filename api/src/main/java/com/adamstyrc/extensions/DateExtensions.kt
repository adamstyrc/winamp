package com.adamstyrc.extensions

import java.text.SimpleDateFormat
import java.util.*

private val YEAR_FORMAT = SimpleDateFormat("yyyy", Locale.US)

fun Date.year() = YEAR_FORMAT.format(this)
