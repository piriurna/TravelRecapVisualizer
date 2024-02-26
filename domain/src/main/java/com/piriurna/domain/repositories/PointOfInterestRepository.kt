package com.piriurna.domain.repositories

import com.piriurna.domain.models.PointOfInterest

interface PointOfInterestRepository {


    suspend fun getAll(): List<PointOfInterest>
}