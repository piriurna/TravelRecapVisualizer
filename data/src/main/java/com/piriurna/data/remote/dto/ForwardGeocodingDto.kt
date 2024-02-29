package com.piriurna.data.remote.dto


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ForwardGeocodingDto(
    @SerializedName("place_id")
    val placeId: Int,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lon")
    val lon: String,
    @SerializedName("boundingbox")
    val boundingbox: List<String>,
    @SerializedName("class")
    val classX: String,
    @SerializedName("importance")
    val importance: Double,
    @SerializedName("licence")
    val licence: String,
    @SerializedName("osm_id")
    val osmId: Long,
    @SerializedName("osm_type")
    val osmType: String,
    @SerializedName("type")
    val type: String
)