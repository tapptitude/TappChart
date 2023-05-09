package com.tappchart.area.model

/**
 * We need to pass in both coordinates, because points are not
 * necessarily equally spaced on the X axis.
 */
data class ChartPoint(
    val x: Float,
    val y: Float,
)