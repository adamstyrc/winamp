package com.adamstyrc.extensions

import com.adamstyrc.models.DEFAULT_LOCALE
import java.text.SimpleDateFormat
import java.util.*

private val YEAR_FORMAT = SimpleDateFormat("yyyy", DEFAULT_LOCALE)

fun Date.year() = YEAR_FORMAT.format(this)!!
