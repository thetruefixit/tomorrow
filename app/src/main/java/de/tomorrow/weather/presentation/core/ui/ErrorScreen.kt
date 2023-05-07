package de.tomorrow.weather.presentation.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.tomorrow.weather.R

@Composable
fun ErrorScreen(
    errorText: String,
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(errorText, color = Color.Black, fontSize = 20.sp)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ErrorPreview() {
    ErrorScreen(
        errorText = stringResource(R.string.error_unknown),
        PaddingValues(10.dp),
    )
}