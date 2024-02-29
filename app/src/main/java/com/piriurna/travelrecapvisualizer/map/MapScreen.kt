package com.piriurna.travelrecapvisualizer.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.piriurna.travelrecapvisualizer.R
import com.piriurna.travelrecapvisualizer.map.utils.MapConstants.DefaultPoiTransitionDuration
import com.piriurna.travelrecapvisualizer.map.utils.MapConstants.DefaultZoomForPoi
import com.piriurna.travelrecapvisualizer.map.utils.MapConstants.MinimumZoomForPoi

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
    val cameraPositionState = rememberCameraPositionState {
        position = createPositionFromLatitudeLongitudeAndZoom(zoom = DefaultZoomForPoi)
    }

    val selectedIndex by remember {
        mutableIntStateOf(0)
    }
    LaunchedEffect(selectedIndex, uiState.pointsOfInterest) {
        uiState.currentPoi(selectedIndex)?.let { poiData ->
            val latLong = LatLng(poiData.latitude, poiData.longitude)

            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(
                    latLong,
                    cameraPositionState.position.zoom.takeIf { it > MinimumZoomForPoi }?:DefaultZoomForPoi
                ),
                DefaultPoiTransitionDuration
            )
        }
    }
    Column(modifier = modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            googleMapOptionsFactory = { GoogleMapOptions() }
        ) {
            uiState.pointsOfInterest.forEach {
                Marker(
                    state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                    title = it.name,
                    snippet = stringResource(R.string.marker_in, it.name),
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

private fun createPositionFromLatitudeLongitudeAndZoom(latLng: LatLng = LatLng(0.0, 0.0), zoom: Float): CameraPosition {
    return CameraPosition.fromLatLngZoom(
        latLng,
        zoom
    )
}