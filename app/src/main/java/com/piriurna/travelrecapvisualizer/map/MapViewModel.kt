package com.piriurna.travelrecapvisualizer.map

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.models.PointOfInterestData
import com.piriurna.domain.usecases.LoadMapPOIUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MapUiState(
    val isLoading: Boolean = false,
    val pointsOfInterest: List<PointOfInterestData> = emptyList()
)
@HiltViewModel
class MapViewModel @Inject constructor(
    private val loadMapPOIUseCase: LoadMapPOIUseCase
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
}