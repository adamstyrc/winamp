package com.adamstyrc.extensions

import java.text.SimpleDateFormat
import java.util.*

val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())

fun Date.year() = yearFormat.format(this).toInt()
