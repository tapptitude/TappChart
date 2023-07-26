package com.tapptitude.tappchart.ui

import androidx.compose.ui.graphics.Brush

//region styles
sealed interface BackgroundStyle {
    // TODO those have to be classes, not object. Object only for demo purposes
    object HorizontalGrid : BackgroundStyle
    object VerticalGrid : BackgroundStyle

    // FIXME think more about the naming
    data class Painter(val brush: Brush)
}
