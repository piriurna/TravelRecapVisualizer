package com.piriurna.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.piriurna.data.database.models.PointOfInterest

@Database(entities = [PointOfInterest::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pointOfInterestDao(): PointOfInterest
}