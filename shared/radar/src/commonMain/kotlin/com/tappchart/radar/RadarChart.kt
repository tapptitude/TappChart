package com.tappchart.radar

import androidx.compose.animation.core.rememberInfiniteTransition
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
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.tappchart.radar.components.drawLabels
import com.tappchart.radar.components.radarChartNet
import com.tappchart.radar.math.highestBetween
import com.tapptitude.tappchart.util.quickForEach
import kotlin.math.max
import kotlin.math.min

@ExperimentalTextApi
@Composable
fun RadarChart(
    modifier: Modifier,
    layersCount: Int,
    pointsCount: Int,
    labels: List<String>,
    angleStartOffset: Float = 0f,
    interiorRadius: Float = 10f,
) {
    check(pointsCount == labels.size) {
        "You must provide an equal number of points and labels!"
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
                    netLayersCount = layersCount,
                    anglesCount = pointsCount,
                    innerSpaceRadius = interiorRadius,
                    circleRadius = circleRadius,
                    startAngleOffset = angleStartOffset,
                )
                drawLabels(
                    startAngleOffset = angleStartOffset.toDouble(),
                    textMeasurer = textMeasurer,
                    labels = labels,
                    radius = circleRadius,
                )
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
            angleStartOffset = rotation,
            layersCount = 5,
            interiorRadius = 40f,
            labels = listOf("label 1", "label 2", "label 3", "label 4", "label 5", "label 6")
        )
    }
}