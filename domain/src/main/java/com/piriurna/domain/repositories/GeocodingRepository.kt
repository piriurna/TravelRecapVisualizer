package com.piriurna.domain.repositories

import com.piriurna.domain.models.PointOfInterestData

interface GeocodingRepository {


    suspend fun getLatLongFromQuery(query: String): PointOfInterestData?
}