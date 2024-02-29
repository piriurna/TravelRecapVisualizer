package com.piriurna.travelrecapvisualizer.addpoi

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.piriurna.travelrecapvisualizer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPoiScreen(
    modifier: Modifier = Modifier,
    viewModel: AddPoiViewModel
) {
    val uiState = viewModel.uiState.value

    var queryText by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            BasicTextField(
                value = queryText,
                onValueChange = { queryText = it },
                decorationBox = {
                    OutlinedTextFieldDefaults.DecorationBox(
                        enabled = true,
                        value = queryText,
                        innerTextField = it,
                        interactionSource = interactionSource,
                        singleLine = true,
                        visualTransformation = VisualTransformation.None,
                        placeholder = { Text("Query") }
                    )
                }
            )

            Button(onClick = { viewModel.searchQuery(queryText) }) {
                Text(text = "Search")
            }
        }

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(
                LatLng(0.0, 0.0),
                0f
            )
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            googleMapOptionsFactory = { GoogleMapOptions() }
        ) {
            uiState.selectedPoi?.let {
                Marker(
                    state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                    title = it.name,
                    snippet = stringResource(R.string.marker_in, it.name),
                )
            }
        }
    }
}