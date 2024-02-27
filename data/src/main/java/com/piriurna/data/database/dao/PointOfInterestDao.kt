package com.piriurna.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.piriurna.data.models.PointOfInterest

@Dao
interface PointOfInterestDao {
    @Query("SELECT * FROM pointofinterest")
    suspend fun getAll(): List<PointOfInterest>

    @Query("SELECT * FROM pointofinterest WHERE id IN (:ids)")
    suspend fun loadAllByIds(ids: IntArray): List<PointOfInterest>

    @Query("SELECT * FROM pointofinterest WHERE latitude LIKE :latitude AND " +
            "longitude LIKE :longitude LIMIT 1"
    )
    suspend fun findByCoordinates(latitude: Double, longitude: Double): PointOfInterest?

    @Insert
    suspend fun insertAll(vararg pointsOfInterest: PointOfInterest)

    @Delete
    suspend fun delete(pointOfInterest: PointOfInterest)
}