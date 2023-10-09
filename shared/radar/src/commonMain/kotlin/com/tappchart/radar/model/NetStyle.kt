package com.tappchart.radar.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke

/**
The style used for drawing the net(grid) on which the polygons lie.
 */

class NetStyle(
    val width: Float = 4f,
    val color: Color = Color.Black,
    val type: DrawStyle = Stroke(),
    val interiorRadius: Float = 10f,
    val layersCount: Int
)