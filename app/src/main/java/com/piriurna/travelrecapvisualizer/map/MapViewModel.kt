package com.piriurna.travelrecapvisualizer.map

import androidx.lifecycle.viewModelScope
import com.piriurna.domain.models.PointOfInterestData
import com.piriurna.domain.usecases.LoadMapPOIUseCase
import com.piriurna.travelrecapvisualizer.common.BaseViewModel
import com.piriurna.travelrecapvisualizer.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MapUiState(
    val isLoading: Boolean = false,
    val pointsOfInterest: List<PointOfInterestData> = emptyList()
): UiState {
    fun currentPoi(index: Int) = pointsOfInterest.getOrNull(index)
}

@HiltViewModel
class MapViewModel @Inject constructor(
    private val loadMapPOIUseCase: LoadMapPOIUseCase
): BaseViewModel<MapUiState>() {

    override fun initialState() = MapUiState()

    init {
        viewModelScope.launch {
            updateState(uiState.value.copy(isLoading = true))

            val result = loadMapPOIUseCase()

            result.collectLatest {
                updateState(uiState.value.copy(isLoading = false, pointsOfInterest = it))
            }

        }
    }
}