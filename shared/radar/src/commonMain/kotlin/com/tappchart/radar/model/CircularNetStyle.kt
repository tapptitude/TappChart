package com.tappchart.radar.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke


data class CircularNetStyle(
    val width: Float = 4f,
    val color: Color = Color.Black,
    val ringsCount: Int,
    val ringsDrawStyle: Stroke,
    val ringsStyle: RingsStyle,
    val linesDrawStyle: Stroke,
    val connectInCenter: Boolean = false,
) {
    // TODO override hashCode & equal
    //  and remove `data` from the name
}

enum class RingsStyle {
    ROUNDED,
    POLYGONS;
}