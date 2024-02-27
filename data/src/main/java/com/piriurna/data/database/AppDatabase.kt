package com.piriurna.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piriurna.data.database.dao.PointOfInterestDao
import com.piriurna.data.models.PointOfInterest

@Database(entities = [PointOfInterest::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pointOfInterestDao(): PointOfInterestDao

    companion object {
        const val DATABASE_NAME = "travelrecapvisualizer.db"
    }
}