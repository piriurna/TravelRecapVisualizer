package com.piriurna.domain.usecases.geocoding

import com.piriurna.domain.models.PointOfInterestData
import com.piriurna.domain.repositories.GeocodingRepository
import javax.inject.Inject

class GetCoordinatesForQueryUseCase @Inject constructor(
    private val geocodingRepository: GeocodingRepository
) {

    suspend operator fun invoke(query: String): PointOfInterestData? {
        return geocodingRepository.getLatLongFromQuery(query)
    }
}