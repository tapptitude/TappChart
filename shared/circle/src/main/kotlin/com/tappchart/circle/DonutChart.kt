package com.tappchart.circle

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.tappchart.circle.model.Slice

@Composable
fun DonutChart(
    modifier: Modifier = Modifier,
    slices: List<Slice>,
    angleOffset: Float,
    donutHoleRatio: Float, /* between 0 and 1 */
    marginBetweenSlices: Dp,
    cornerRadius: CornerSize,
    labelStyle: TextStyle,
) {
}