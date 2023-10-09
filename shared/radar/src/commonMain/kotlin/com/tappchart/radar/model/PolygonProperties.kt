package com.tappchart.radar.model

import androidx.compose.ui.graphics.Color

/**
 * The information necessary for drawing a polygon on the screen, for a [RadarEntry].
 *
 * @param borderColor the [Color] used for drawing the borders of the polygon. Mandatory
 * @param borderWidth the width of the border of the polygon. Mandatory
 * @param areaColor the color for the area inside the [borderColor]
 * @param dotsRadius if different from 0f, dots will be drawn at the intersection between 2 lines of the border
 */
data class PolygonProperties(
    val borderColor: Color,
    val borderWidth: Float,
    val areaColor: Color = Color.Unspecified,
    val dotsRadius: Float = 0f,
)