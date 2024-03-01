package com.piriurna.travelrecapvisualizer.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.piriurna.travelrecapvisualizer.common.components.AppTopBar
import com.piriurna.travelrecapvisualizer.ui.theme.TravelRecapVisualizerTheme

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    pageTitle: String? = null,
    isBackEnabled: Boolean = false,
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit
) {

    TravelRecapVisualizerTheme {
        Scaffold(
            modifier = modifier,
            topBar = {
                if (pageTitle != null)
                    AppTopBar(
                        modifier = Modifier,
                        title = pageTitle,
                        isBackEnabled = isBackEnabled,
                        onBackPressed = { navController.popBackStack() }
                    )
            },
        ) {
            content(it)
        }
    }
}