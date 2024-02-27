package com.piriurna.data.repositories

import com.piriurna.data.database.dao.PointOfInterestDao
import com.piriurna.domain.models.PointOfInterestData
import com.piriurna.domain.repositories.PointOfInterestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PointOfInterestRepositoryImpl @Inject constructor(
    private val pointOfInterestDao: PointOfInterestDao
): PointOfInterestRepository {

    override suspend fun getAll(): Flow<List<PointOfInterestData>> {
        return pointOfInterestDao.getAll().map {
            it.map { poi ->
                with(poi) {
                    PointOfInterestData(
                        id = id,
                        latitude = latitude,
                        longitude = longitude,
                        markerRes = 0,
                        name = name
                    )

                }
            }
        }
    }
}