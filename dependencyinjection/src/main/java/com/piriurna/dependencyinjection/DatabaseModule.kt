package com.piriurna.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.piriurna.data.database.AppDatabase
import com.piriurna.data.database.AppDatabase.Companion.DATABASE_NAME
import com.piriurna.data.database.dao.PointOfInterestDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun providePOIDao(
        appDatabase: AppDatabase
    ): PointOfInterestDao {
        return appDatabase.pointOfInterestDao()
    }
}