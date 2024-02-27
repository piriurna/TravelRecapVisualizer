package com.piriurna.domain.repositories

import com.piriurna.domain.models.PointOfInterestData
import kotlinx.coroutines.flow.Flow

interface PointOfInterestRepository {


    suspend fun getAll(): Flow<List<PointOfInterestData>>
}