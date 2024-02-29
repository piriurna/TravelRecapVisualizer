package com.piriurna.data.remote.sources

import com.piriurna.data.remote.GeocodingApi
import com.piriurna.data.remote.HandleApi
import com.piriurna.data.remote.dto.ForwardGeocodingDto
import javax.inject.Inject

class GeocodingApiSource @Inject constructor(
    private val geocodingApi: GeocodingApi
){

    suspend fun searchForwardGeocoding(query: String): List<ForwardGeocodingDto> {
        return HandleApi.safeApiCall { geocodingApi.searchForwardGeocoding(query) }
    }
}