package com.tappchart.circle.model

import androidx.compose.ui.graphics.Color
import com.tappchart.circle.model.LabelPosition

data class Slice(
    val key: String, // the label acts as a key too
    val percentage: Float,
    val labelPosition: LabelPosition,
    val color: Color,
)
