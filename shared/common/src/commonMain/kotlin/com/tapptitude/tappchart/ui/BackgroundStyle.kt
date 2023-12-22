package com.tapptitude.tappchart.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.DrawScope

//region styles
sealed interface BackgroundStyle {
    // TODO those have to be classes, not object. Object only for demo purposes
    data object HorizontalGrid : BackgroundStyle
    data object VerticalGrid : BackgroundStyle

    // FIXME think more about the naming
    data class Painter(val brush: Brush) : BackgroundStyle
}

fun DrawScope.drawBackgroundGradient(brush: Brush, startOffset: Offset, endOffset: Offset) {
    val width = endOffset.x - startOffset.x
    val height = endOffset.y - startOffset.y
    this.drawRect(
        brush = brush,
        topLeft = startOffset,
        size = Size(
            width = if (width < 0) 0f else width,
            height = if (height < 0) 0f else height
        )
    )
}