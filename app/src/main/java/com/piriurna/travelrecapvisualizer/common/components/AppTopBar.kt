package com.piriurna.travelrecapvisualizer.common.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackPressed: () -> Unit = {},
    isBackEnabled: Boolean = false,
    containerColor: Color = MaterialTheme.colorScheme.primary
) {

    TopAppBar(
        modifier = modifier,
        title = { Text(text = title, color = contentColorFor(backgroundColor = containerColor)) },
        navigationIcon = {
            if(isBackEnabled)
                Icon(
                    modifier = Modifier.clickable(enabled = true, onClick = onBackPressed),
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null,
                    tint = contentColorFor(backgroundColor = containerColor)
                )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = containerColor)
    )
}