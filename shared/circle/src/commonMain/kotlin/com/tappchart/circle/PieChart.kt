package com.tappchart.circle

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tappchart.circle.model.Slice

/**
 * @param angleOffset by default, drawing starts from 12 o'clock.
 * You can provide a value, [0, 359] to offset the start drawing position
 */
@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    slices: List<Slice>,
    angleOffset: Float = 0f,
    marginBetweenSlices: Dp = 0.dp,
    labelStyle: TextStyle,
) {
}
