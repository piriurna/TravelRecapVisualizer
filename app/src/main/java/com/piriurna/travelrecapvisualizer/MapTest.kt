package com.piriurna.travelrecapvisualizer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapTest() {
    val markers = listOf(
        LatLng(1.35, 103.87),
        LatLng(2.35, 102.87),
        LatLng(3.35, 101.87)
    )
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        markers.forEach {
            Marker(
                state = MarkerState(position = it),
                title = "Singapore",
                snippet = "Marker in Singapore"
            )
        }
    }
}


@Preview
@Composable
fun MapTestPreview() {
    MaterialTheme {
        Surface {
            MapTest()
        }
    }
}