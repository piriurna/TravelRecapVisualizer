package com.piriurna.travelrecapvisualizer.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel()
) {
    MapScreenContent(modifier = modifier, uiState = viewModel.uiState.value)
}

@Composable
private fun MapScreenContent(
    modifier: Modifier = Modifier,
    uiState: MapUiState
) {
    Column(modifier = modifier.fillMaxSize()) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(
                LatLng(
                    uiState.pointsOfInterest.firstOrNull()?.latitude?:0.0,
                    uiState.pointsOfInterest.firstOrNull()?.longitude?:0.0
                ),
                10f
            )
        }

        LaunchedEffect(uiState.pointsOfInterest) {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                LatLng(
                    uiState.pointsOfInterest.firstOrNull()?.latitude?:0.0,
                    uiState.pointsOfInterest.firstOrNull()?.longitude?:0.0
                ),
                10f
            )
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            uiState.pointsOfInterest.forEach {
                Marker(
                    state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                    title = it.name,
                    snippet = "Marker in ${it.name}",
                )
            }
        }
    }
}

@Preview
@Composable
fun MapScreenPreview() {
    MaterialTheme {
        Surface {
            MapScreenContent(uiState = MapUiState())
        }
    }
}