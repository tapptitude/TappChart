package com.tappchart.desktop

import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.tappchart.radar.Preview_RadarChart

@OptIn(ExperimentalTextApi::class)
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TappChart",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        content = { Preview_RadarChart() },
    )
}
