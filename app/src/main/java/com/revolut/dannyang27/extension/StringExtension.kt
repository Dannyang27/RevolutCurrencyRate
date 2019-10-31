package com.revolut.dannyang27.extension

fun String.isNumeric(): Boolean = this.matches("-?\\d+(\\.\\d+)?".toRegex())