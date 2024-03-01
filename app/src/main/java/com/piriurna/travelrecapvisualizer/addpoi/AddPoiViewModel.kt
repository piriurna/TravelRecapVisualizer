package com.piriurna.travelrecapvisualizer.addpoi

import androidx.lifecycle.viewModelScope
import com.piriurna.domain.models.PointOfInterestData
import com.piriurna.domain.usecases.CreateNewPoiUseCase
import com.piriurna.domain.usecases.geocoding.GetCoordinatesForQueryUseCase
import com.piriurna.travelrecapvisualizer.common.BaseViewModel
import com.piriurna.travelrecapvisualizer.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddPoiUiState(
    val isLoading: Boolean = false,
    val selectedPoi: PointOfInterestData? = null,
): UiState

@HiltViewModel
class AddPoiViewModel @Inject constructor(
    private val getCoordinatesForQueryUseCase: GetCoordinatesForQueryUseCase,
    private val createNewPoiUseCase: CreateNewPoiUseCase
): BaseViewModel<AddPoiUiState>() {

    override fun initialState() = AddPoiUiState()

    fun searchQuery(query: String) {
        if (query.isEmpty()) return

        viewModelScope.launch {
            updateState(uiState.value.copy(isLoading = true))
            updateState(uiState.value.copy(
                isLoading = false,
                selectedPoi = getCoordinatesForQueryUseCase(query)
            ))
        }
    }

    fun addPoi() {
        viewModelScope.launch {
            updateState(uiState.value.copy(isLoading = true))
            uiState.value.selectedPoi?.let {
                createNewPoiUseCase(it)
                updateState(uiState.value.copy(isLoading = false))
            }
        }
    }
}