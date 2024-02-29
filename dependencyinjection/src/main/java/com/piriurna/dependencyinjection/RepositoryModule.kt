package com.piriurna.dependencyinjection

import com.piriurna.data.database.dao.PointOfInterestDao
import com.piriurna.data.remote.sources.GeocodingApiSource
import com.piriurna.data.repositories.GeocodingRepositoryImpl
import com.piriurna.data.repositories.PointOfInterestRepositoryImpl
import com.piriurna.domain.repositories.GeocodingRepository
import com.piriurna.domain.repositories.PointOfInterestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePointOfInterestRepository(pointOfInterestDao: PointOfInterestDao): PointOfInterestRepository {
        return PointOfInterestRepositoryImpl(pointOfInterestDao)
    }


    @Provides
    @Singleton
    fun provideGeocodingRepository(geocodingApiSource: GeocodingApiSource): GeocodingRepository {
        return GeocodingRepositoryImpl(geocodingApiSource)
    }
}