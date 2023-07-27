package com.tappchart.desktop

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.tappchart.radar.Preview_RadarChart
import com.tapptitude.tappchart.HelloWorld

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TappChart",
        state = rememberWindowState(width = 800.dp, height = 600.dp)
    ) {
//        HelloWorld(
//            modifier = Modifier.fillMaxSize(),
//            platform = "Desktop",
//        )
        Preview_RadarChart()
    }
}
