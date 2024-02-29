package com.piriurna.data.remote

import com.piriurna.data.remote.dto.ForwardGeocodingDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApi {

    @GET("search")
    suspend fun searchForwardGeocoding(
        @Query("q") query: String
    ): List<ForwardGeocodingDto>
}