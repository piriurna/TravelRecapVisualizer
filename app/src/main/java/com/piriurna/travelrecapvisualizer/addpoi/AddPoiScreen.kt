package com.piriurna.travelrecapvisualizer.addpoi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.piriurna.travelrecapvisualizer.R
import com.piriurna.travelrecapvisualizer.common.components.CustomTextField


@Composable
fun AddPoiScreen(
    modifier: Modifier = Modifier,
    viewModel: AddPoiViewModel
) {
    val uiState = viewModel.uiState.value

    var queryText by remember { mutableStateOf("") }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(0.0, 0.0),
            0f
        )

    }

    LaunchedEffect(uiState.selectedPoi) {
        if(uiState.selectedPoi == null) return@LaunchedEffect

        val latLng = LatLng(uiState.selectedPoi.latitude, uiState.selectedPoi.longitude)
        cameraPositionState.animate(
            CameraUpdateFactory.newLatLngZoom(
                latLng,
                6f
            )
        )
    }

    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.fillMaxWidth().height(50.dp)) {

            CustomTextField(
                modifier = Modifier.width(300.dp).fillMaxHeight(),
                value = queryText,
                placeholder = stringResource(R.string.search_a_place),
                onValueChange = { queryText = it }
            )

            Button(
                modifier = Modifier
                    .fillMaxHeight(),
                onClick = { viewModel.searchQuery(queryText) },
                shape = RectangleShape
            ) {
                Text(text = stringResource(R.string.search))
            }
        }


        uiState.selectedPoi?.let {
            GoogleMap(
                modifier = Modifier
                    .padding(32.dp)
                    .size(250.dp)
                    .clip(RoundedCornerShape(15)),
                cameraPositionState = cameraPositionState,
                googleMapOptionsFactory = { GoogleMapOptions() },

                uiSettings = MapUiSettings(
                    compassEnabled = false,
                    mapToolbarEnabled = false,
                    zoomControlsEnabled = false,
                    scrollGesturesEnabled = false
                )
            ) {
                Marker(
                    state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                    title = it.name,
                    snippet = stringResource(R.string.marker_in, it.name),
                )
            }

           Text(text = uiState.selectedPoi.name, style = MaterialTheme.typography.headlineMedium)

           Text(
               text = stringResource(
                   R.string.lat_lon,
                   uiState.selectedPoi.latitude,
                   uiState.selectedPoi.longitude
               ),
               style = MaterialTheme.typography.labelLarge,
               color = Color.Gray
           )

            Button(onClick = viewModel::addPoi) {
                Text(text = stringResource(R.string.add_place))
            }
        }
    }
}