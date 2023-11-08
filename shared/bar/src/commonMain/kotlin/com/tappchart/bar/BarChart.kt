@file:OptIn(ExperimentalTextApi::class, ExperimentalTextApi::class)

package com.tappchart.bar

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
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
    yAxisPaddingToFirstBar: Dp = 24.dp,
    yAxisPaddingToLine: Dp = 8.dp,
    xAxisPaddingToBars:Dp = 2.dp
) {

    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = modifier) {

        //region vertical labels
        val firstLabelSize = textMeasurer.measure(verticalLabel.first().label).size
        val firstLabelPosition = size.height - firstLabelSize.height/2
        val maxWidth = verticalLabel.maxOf { textMeasurer.measure(it.label).size.width }.toFloat()

        var bottomOfChart = size.height
        verticalLabel.forEachIndexed { index, label ->

            val labelPosition = firstLabelPosition - (size.height * label.yValue) / yAxisInterval.max
            val labelSize = textMeasurer.measure(label.label, labelStyle)
            drawText(
                textLayoutResult = labelSize,
                topLeft = Offset(maxWidth - labelSize.size.width, labelPosition)
            )
            val lineY = labelPosition + labelSize.size.height / 2
            if (index == 0) bottomOfChart = lineY
            val startLine = maxWidth + yAxisPaddingToLine.toPx()
            drawLine(
                color = Color.Gray,
                start = Offset(startLine, lineY),
                end = Offset(size.width, lineY)
            )
        }
        //endregion

        //region bars
        val startBarsPadding = yAxisPaddingToFirstBar.toPx()
        var barTopLeftPosition = startBarsPadding
        val barsCount = data.size
        val barWidth = (size.width - spacingBetweenBars.toPx() * barsCount - startBarsPadding) / barsCount
        val differenceOfHeightOfCanvasAndFirstLabel = size.height - bottomOfChart
        data.forEachIndexed { index, bar ->
            val color = barColor.getOrNull(index) ?: barColor.first()
            drawRect(
                color = color,
                topLeft = Offset(
                    x = barTopLeftPosition,
                    y = size.height - (bar.value * size.height) / yAxisInterval.max
                ),
                size = Size(
                    barWidth,
                    (bar.value * size.height) / yAxisInterval.max - differenceOfHeightOfCanvasAndFirstLabel
                )
            )
            barTopLeftPosition += barWidth + spacingBetweenBars.toPx()

            //xAxis labels
            val horizontalLabelSize = textMeasurer.measure(bar.key.label, labelStyle)
            drawText(
                textLayoutResult = horizontalLabelSize,
                topLeft = Offset(barTopLeftPosition - barWidth - spacingBetweenBars.toPx(), size.height + xAxisPaddingToBars.toPx())
            )
        }
        //endregion
    }
}