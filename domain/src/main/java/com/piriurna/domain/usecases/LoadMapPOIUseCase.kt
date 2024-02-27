package com.piriurna.domain.usecases

import com.piriurna.domain.models.PointOfInterest
import com.piriurna.domain.repositories.PointOfInterestRepository
import javax.inject.Inject

class LoadMapPOIUseCase @Inject constructor(
    private val poiRepository: PointOfInterestRepository
) {
    suspend operator fun invoke(): List<PointOfInterest> {
        return poiRepository.getAll()
    }
}