package com.piriurna.travelrecapvisualizer.di

import com.piriurna.travelrecapvisualizer.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("api_key")
    fun provideApiKey(): String {
        return BuildConfig.GEOCODING_API_KEY
    }
}