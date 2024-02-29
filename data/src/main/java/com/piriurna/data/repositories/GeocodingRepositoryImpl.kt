package com.piriurna.data.repositories

import com.piriurna.data.remote.sources.GeocodingApiSource
import com.piriurna.domain.models.PointOfInterestData
import com.piriurna.domain.repositories.GeocodingRepository
import javax.inject.Inject

class GeocodingRepositoryImpl @Inject constructor(
    private val geocodingApiSource: GeocodingApiSource
): GeocodingRepository {

    override suspend fun getLatLongFromQuery(query: String): PointOfInterestData? {
        val result = geocodingApiSource.searchForwardGeocoding(query).firstOrNull()
        return result?.let {
            PointOfInterestData(
                id = it.placeId,
                name = it.displayName,
                latitude = it.lat.toDoubleOrNull()?:0.0,
                longitude = it.lon.toDoubleOrNull()?:0.0,
            )
        }
    }
}