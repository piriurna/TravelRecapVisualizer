package com.piriurna.data.database.repositories

import com.piriurna.data.database.dao.PointOfInterestDao
import com.piriurna.domain.models.PointOfInterest
import com.piriurna.domain.repositories.PointOfInterestRepository

class PointOfInterestRepositoryImpl(
    private val pointOfInterestDao: PointOfInterestDao
): PointOfInterestRepository {

    override suspend fun getAll(): List<PointOfInterest> {
        return pointOfInterestDao.getAll().map {
            PointOfInterest(
                id = it.id,
                latitude = it.latitude,
                longitude = it.longitude,
                markerRes = 0
            )
        }
    }
}