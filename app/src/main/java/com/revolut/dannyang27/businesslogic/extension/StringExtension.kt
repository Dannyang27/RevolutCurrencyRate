package com.revolut.dannyang27.businesslogic.extension

fun String.isNumeric(): Boolean = this.matches("-?\\d+(\\.\\d+)?".toRegex())