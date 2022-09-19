package com.babel.demo.dogapp.data.utils

const val EMPTY = ""

fun String?.checkNull(defaultValue: String = EMPTY) = this ?: defaultValue