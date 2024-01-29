package com.tappchart.desktop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.tappchart.bar.BarChart
import com.tappchart.bar.model.Bar
import com.tapptitude.tappchart.model.AxisInterval
import com.tapptitude.tappchart.model.DataLabel
import com.tapptitude.tappchart.model.ValueLabel
import com.tapptitude.tappchart.ui.AxisStyle
import com.tapptitude.tappchart.ui.BackgroundStyle
import kotlinx.collections.immutable.persistentListOf

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TappChart",
        state = rememberWindowState(width = 800.dp, height = 600.dp)
    ) {
        Column {
            TestBarChart1()
            TestBarChart2()
        }
    }
}


@Composable
private fun TestBarChart1(modifier: Modifier = Modifier) {
    BarChart(
        modifier = modifier
            .width(400.dp)
            .height(300.dp)
            .padding(24.dp),
        yAxisInterval = AxisInterval(8f, 0f),
        data = persistentListOf(
            Bar(DataLabel("Guest"), 1.7f),
            Bar(DataLabel("New User"), 3f),
            Bar(DataLabel("Existing User"), 8f),
            Bar(DataLabel("Test User"), 5.5f),
            Bar(DataLabel("Test User 2"), 6.2f)
        ),
        verticalLabels = persistentListOf(
            ValueLabel("0", 0f),
            ValueLabel("2K", 2f),
            ValueLabel("4K", 4f),
            ValueLabel("6K", 6f),
            ValueLabel("8K", 8f)
        ),
        labelStyle = TextStyle.Default.copy(fontSize = 10.sp),
        spacingBetweenBars = 16.dp,
        axisStyle = AxisStyle(
            1.dp,
            Brush.linearGradient(listOf(Color(0xFFECEBEB), Color(0xFFECEBEB)))
        ),
        barColor = persistentListOf(Color.Blue, Color.Blue, Color.Red),
        showTopLabelMisaligned = false
    )
}

@Composable
private fun TestBarChart2(modifier: Modifier = Modifier) {
    BarChart(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(24.dp),
        yAxisInterval = AxisInterval(28f, 0f),
        data = persistentListOf(
            Bar(DataLabel("Apr 2020"), 22f),
            Bar(DataLabel("May 2020"), 21.5f),
            Bar(DataLabel("Jun 2020"), 23.5f),
            Bar(DataLabel("Jul 2020"), 26f),
            Bar(DataLabel("Aug 2020"), 22f),
            Bar(DataLabel("Sep 2020"), 21.5f)
        ),
        verticalLabels = persistentListOf(
            ValueLabel("0", 0f),
            ValueLabel("8K", 8f),
            ValueLabel("16K", 16f),
            ValueLabel("24K", 24f)
        ),
        labelStyle = TextStyle.Default.copy(fontSize = 10.sp),
        spacingBetweenBars = 8.dp,
        axisStyle = AxisStyle(
            1.dp,
            Brush.linearGradient(listOf(Color(0xFFECEBEB), Color(0xFF616161)))
        ),
        barColor = persistentListOf(Color.Blue),
        background = persistentListOf(
            BackgroundStyle.Painter(
                Brush.horizontalGradient(
                    listOf(Color.Cyan, Color.Magenta)
                )
            ),
            BackgroundStyle.HorizontalGrid
        ),
        firstBarStartPadding = 8.dp,
        lastBarEndPadding = 8.dp
    )
}
