package com.revolut.dannyang27.model

data class Rate(val base: String, val date: String, val rates: Map<String, Double>)