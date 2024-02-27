package com.piriurna.domain.models

data class PointOfInterestData(
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val markerRes: Int,
    val name: String,
)