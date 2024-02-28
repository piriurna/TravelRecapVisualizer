package com.piriurna.domain.usecases

import com.piriurna.domain.models.PointOfInterestData
import com.piriurna.domain.repositories.PointOfInterestRepository
import javax.inject.Inject

class CreateNewPoiUseCase @Inject constructor(
    private val poiRepository: PointOfInterestRepository
) {


    suspend operator fun invoke(pointOfInterestData: PointOfInterestData) {
        poiRepository.createNewPoi(pointOfInterestData)
    }
}