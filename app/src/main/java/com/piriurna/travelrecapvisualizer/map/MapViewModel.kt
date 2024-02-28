package com.piriurna.travelrecapvisualizer.map

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.piriurna.domain.models.PointOfInterestData
import com.piriurna.domain.usecases.CreateNewPoiUseCase
import com.piriurna.domain.usecases.LoadMapPOIUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MapUiState(
    val isLoading: Boolean = false,
    val pointsOfInterest: List<PointOfInterestData> = emptyList()
){
    fun currentPoi(index: Int) = pointsOfInterest.getOrNull(index)
}

@HiltViewModel
class MapViewModel @Inject constructor(
    private val loadMapPOIUseCase: LoadMapPOIUseCase,
    private val createNewPoiUseCase: CreateNewPoiUseCase
): ViewModel() {

    private val _uiState: MutableState<MapUiState> = mutableStateOf(MapUiState())
    val uiState: State<MapUiState> = _uiState


    init {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val result = loadMapPOIUseCase()

            result.collectLatest {
                _uiState.value = _uiState.value.copy(isLoading = false, pointsOfInterest = it)
            }

        }
    }

    fun addNewPoi(latLng: LatLng) {
        viewModelScope.launch {
            createNewPoiUseCase.invoke(
                PointOfInterestData(
                    name = "asda",
                    latitude = latLng.latitude,
                    longitude = latLng.longitude,
                    markerRes = 0
                )
            )
        }
    }
}