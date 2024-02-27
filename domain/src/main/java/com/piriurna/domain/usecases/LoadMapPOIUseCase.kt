package com.piriurna.domain.usecases

import com.piriurna.domain.models.PointOfInterestData
import com.piriurna.domain.repositories.PointOfInterestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadMapPOIUseCase @Inject constructor(
    private val poiRepository: PointOfInterestRepository
) {
    suspend operator fun invoke(): Flow<List<PointOfInterestData>> {
        return poiRepository.getAll()
    }
}