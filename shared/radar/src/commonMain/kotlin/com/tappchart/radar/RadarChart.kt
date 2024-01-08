package com.tappchart.radar

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntSize
import com.tappchart.radar.components.drawLabels
import com.tappchart.radar.components.drawPolygon
import com.tappchart.radar.components.radarChartNet
import com.tappchart.radar.math.highestBetween
import com.tappchart.radar.model.CircularNetStyle
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
    netStyle: CircularNetStyle,
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

                entries.quickForEach { entry ->
                    drawPolygon(
                        pointsCount = pointsCount,
                        startAngleOffset = angleStartOffset,
                        entry = entry,
                        circleRadius = circleRadius,
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


