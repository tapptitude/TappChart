@file:OptIn(ExperimentalTextApi::class, ExperimentalTextApi::class)

package com.tappchart.bar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tappchart.bar.model.Bar
import com.tapptitude.tappchart.model.AxisInterval
import com.tapptitude.tappchart.model.ValueLabel
import com.tapptitude.tappchart.ui.AxisStyle
import com.tapptitude.tappchart.ui.BackgroundStyle


@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    yAxisInterval: AxisInterval,
    data: List<Bar>,
    verticalLabel: List<ValueLabel>,
    labelStyle: TextStyle,
    spacingBetweenBars: Dp,
    axisStyle: AxisStyle,
    background: List<BackgroundStyle> = listOf(),
    barColor: List<Color>,
    yAxisPadding:Dp = 24.dp
) {

    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = modifier) {
        drawYAxis(verticalLabel, yAxisInterval, textMeasurer)

        val startBarsPadding = yAxisPadding.toPx()
        var barTopLeft = startBarsPadding
        val barWidth = (size.width - (spacingBetweenBars.toPx() * (data.size)) - startBarsPadding) / data.size
        data.forEachIndexed { index, bar ->
            val color = barColor[index]

            drawRect(
                color = color,
                topLeft = Offset(
                    x = barTopLeft,
                    y = size.height - (bar.value * size.height) / yAxisInterval.max
                ),
                size = Size(barWidth, (bar.value * size.height) / yAxisInterval.max)
            )
            barTopLeft += barWidth + spacingBetweenBars.toPx()
        }
    }
}

fun DrawScope.drawYAxis(
    verticalLabel: List<ValueLabel>,
    yAxisInterval: AxisInterval,
    textMeasurer: TextMeasurer
) {
    val firstLabelPosition =
        size.height - textMeasurer.measure(verticalLabel.first().label).size.height
    verticalLabel.forEach { label ->
        val newPos = firstLabelPosition - (size.height * label.yValue) / yAxisInterval.max
        drawText(
            textMeasurer = textMeasurer,
            text = label.label,
            topLeft = Offset(0f, newPos)
        )
    }
}