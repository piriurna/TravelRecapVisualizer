package com.piriurna.data.mapper

import com.piriurna.data.models.PointOfInterest
import com.piriurna.domain.models.PointOfInterestData

fun PointOfInterest.toDomain(): PointOfInterestData {
    return PointOfInterestData(
        id = id,
        name = name,
        latitude = latitude,
        longitude = longitude,
        markerRes = 0
    )
}


fun PointOfInterestData.toDto(): PointOfInterest {
    return PointOfInterest(
        name = name,
        latitude = latitude,
        longitude = longitude,
    )
}