package com.tappchart.radar

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.tappchart.radar.components.radarChartNet
import com.tappchart.radar.math.PointCalculator

@Composable
fun RadarChart(
    modifier: Modifier,
    layersCount: Int,
    pointsCount: Int,
    angleStartOffset: Float = 0f,
    interiorRadius: Float = 10f,
    radius: Float = 100f,
) {
    Box(modifier = modifier.then(
        Modifier.drawWithCache {
            onDrawWithContent {
                radarChartNet(
                    netLayersCount = layersCount,
                    anglesCount = pointsCount,
                    innerSpaceRadius = interiorRadius,
                    circleRadius = radius,
                    startAngleOffset = angleStartOffset,
                )
            }
        }
    ))
}


@Suppress("FunctionName")
@Preview
@Composable
fun Preview_RadarChart() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1_000),
            repeatMode = RepeatMode.Restart,
        )
    )

    Box(
        modifier = Modifier.size(401.dp).border(
            1.dp, Color.Black,
        ).padding(1.dp),
        contentAlignment = Alignment.Center,
    ) {
        RadarChart(
            modifier = Modifier.size(400.dp),
            pointsCount = 7,
            angleStartOffset = -90f + rotation,
            radius = with(LocalDensity.current) { 100.dp.toPx() },
            layersCount = 5,
            interiorRadius = 40f
        )
    }
}