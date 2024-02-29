package com.piriurna.travelrecapvisualizer.addpoi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piriurna.domain.models.PointOfInterestData
import com.piriurna.domain.usecases.geocoding.GetCoordinatesForQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddPoiUiState(
    val selectedPoi: PointOfInterestData? = null
)

@HiltViewModel
class AddPoiViewModel @Inject constructor(
    private val getCoordinatesForQueryUseCase: GetCoordinatesForQueryUseCase
): ViewModel() {

    private val _uiState = mutableStateOf(AddPoiUiState())
    val uiState: State<AddPoiUiState> = _uiState

    fun searchQuery(query: String) {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                selectedPoi = getCoordinatesForQueryUseCase(query)
            )
        }
    }
}