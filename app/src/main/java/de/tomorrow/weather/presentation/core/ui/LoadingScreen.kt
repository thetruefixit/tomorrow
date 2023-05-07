package de.tomorrow.weather.presentation.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.tomorrow.weather.presentation.theme.BlueCatalina
import de.tomorrow.weather.presentation.theme.WhiteLilac

@Composable
fun LoadingScreen(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(BlueCatalina, WhiteLilac),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                )
            )
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = Color.White
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewLoadingScreen() {
    LoadingScreen(PaddingValues(10.dp))
}