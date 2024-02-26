package com.piriurna.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.piriurna.data.database.models.PointOfInterest

@Dao
interface PointOfInterestDao {
    @Query("SELECT * FROM pointofinterest")
    fun getAll(): List<PointOfInterest>

    @Query("SELECT * FROM pointofinterest WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<PointOfInterest>

    @Query("SELECT * FROM pointofinterest WHERE latitude LIKE :latitude AND " +
            "longitude LIKE :longitude LIMIT 1"
    )
    fun findByCoordinates(latitude: Double, longitude: Double): PointOfInterest?

    @Insert
    fun insertAll(vararg pointsOfInterest: PointOfInterest)

    @Delete
    fun delete(pointOfInterest: PointOfInterest)
}