package com.tappchart.radar

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.tappchart.radar.components.drawLabels
import com.tappchart.radar.components.drawPolygon
import com.tappchart.radar.components.radarChartNet
import com.tappchart.radar.math.highestBetween
import com.tappchart.radar.model.NetStyle
import com.tappchart.radar.model.PolygonProperties
import com.tappchart.radar.model.RadarEntry
import com.tapptitude.tappchart.util.quickForEach
import kotlin.math.max
import kotlin.math.min

@ExperimentalTextApi
@Composable
fun RadarChart(
    modifier: Modifier,
    pointsCount: Int,
    entries: List<RadarEntry>,
    labels: List<String>,
    labelStyle: TextStyle = TextStyle.Default,
    netStyle: NetStyle,
    angleStartOffset: Float = 0f,
) {
    check(pointsCount == labels.size) {
        "You must provide an equal number of points, labels!"
    }

    val textMeasurer = rememberTextMeasurer()

    val textHighestSize = remember(labels) { calculateMaxTextSizes(labels, textMeasurer) }

    Box(modifier = modifier.then(
        Modifier.drawWithCache {
            val circleRadius = min(
                a = this.size.height, b = this.size.width,
            ) / 2 - max(textHighestSize.height, textHighestSize.width)

            onDrawWithContent {
                // Inset the drawing space with the space necessary for text
                radarChartNet(
                    pointsCount = pointsCount,
                    circleRadius = circleRadius,
                    startAngleOffset = angleStartOffset,
                    netStyle = netStyle
                )
                drawLabels(
                    startAngleOffset = angleStartOffset.toDouble(),
                    textMeasurer = textMeasurer,
                    labels = labels,
                    labelStyle = labelStyle,
                    radius = circleRadius,
                )

                entries.forEach { entry ->
                    drawPolygon(
                        pointsCount = pointsCount,
                        startAngleOffset = angleStartOffset,
                        entry = entry
                    )
                }
            }
        }
    ))
}


@ExperimentalTextApi
private fun calculateMaxTextSizes(labels: List<String>, textMeasurer: TextMeasurer): IntSize {
    var maxWidth = 0
    var maxHeight = 0

    labels.quickForEach { label ->
        val size = textMeasurer.measure(label).size

        maxHeight = highestBetween(maxHeight, size.height)
        maxWidth = highestBetween(maxWidth, size.width)
    }

    return IntSize(width = maxWidth, height = maxHeight)
}


@ExperimentalTextApi
@Suppress("FunctionName")
@Preview
@Composable
fun Preview_RadarChart() {
//    val infiniteTransition = rememberInfiniteTransition()
//    val rotation by infiniteTransition.animateFloat(
//        initialValue = 0f,
//        targetValue = 360f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 10_000, easing = LinearEasing),
//            repeatMode = RepeatMode.Restart,
//        )
//    )

    var rotation by remember { mutableStateOf(0f) }
    val state = rememberTransformableState { _, _, rotationChange ->
        rotation += rotationChange
    }


    Box(
        modifier = Modifier.fillMaxSize().transformable(state).border(
            1.dp, Color.Black,
        ).padding(10.dp),
        contentAlignment = Alignment.Center,
    ) {
        RadarChart(
            modifier = Modifier.fillMaxSize(),
            pointsCount = 6,
            angleStartOffset = 0f,
            entries = listOf(
                RadarEntry(
                    values = listOf(25f, 110f, 190f, 75f, 200f, 140f),
                    PolygonProperties(
                        borderColor = Color.Magenta,
                        borderWidth = 3f,
                        areaColor = Color.Magenta.copy(alpha = 0.3f),
                        dotsRadius = 10f
                    )
                ),
                RadarEntry(
                    values = listOf(250f, 310f, 90f, 150f, 20f, 400f),
                    PolygonProperties(
                        borderColor = Color.Cyan,
                        borderWidth = 3f,
                        areaColor = Color.Cyan.copy(alpha = 0.3f),
                        dotsRadius = 10f
                    )
                ),
                RadarEntry(
                    values = listOf(305f, 200f, 360f, 400f, 160f, 390f),
                    PolygonProperties(
                        borderColor = Color.Green,
                        borderWidth = 3f,
                        areaColor = Color.Green.copy(alpha = 0.3f),
                        dotsRadius = 10f
                    )
                ),
                RadarEntry(
                    values = listOf(100f, 350f, 30f, 175f, 267f, 440f),
                    PolygonProperties(
                        borderColor = Color.Red,
                        borderWidth = 3f,
                        areaColor = Color.Red.copy(alpha = 0.3f),
                        dotsRadius = 10f
                    )
                ),
                RadarEntry(
                    values = listOf(215f, 310f, 200f, 15f, 0f, 180f),
                    PolygonProperties(
                        borderColor = Color.Yellow,
                        borderWidth = 3f,
                        areaColor = Color.Yellow.copy(alpha = 0.3f),
                        dotsRadius = 10f
                    )
                ),
            ),
            labels = listOf("label 1", "label 2", "label 3", "label 4", "label 5", "label 6"),
            labelStyle = TextStyle.Default.copy(fontStyle = FontStyle.Italic),
            netStyle = NetStyle(
                width = 4f,
                color = Color.Black,
                type = Stroke(),
                interiorRadius = 50f,
                layersCount = 5,
            ),
        )
    }
}