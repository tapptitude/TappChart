@file:OptIn(ExperimentalTextApi::class)

package com.tappchart.bar

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextLayoutResult
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
import com.tapptitude.tappchart.ui.drawBackgroundGradient

@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    yAxisInterval: AxisInterval,
    data: List<Bar>,
    verticalLabels: List<ValueLabel>,
    labelStyle: TextStyle,
    spacingBetweenBars: Dp,
    axisStyle: AxisStyle,
    barColor: List<Color>,
    background: List<BackgroundStyle> = listOf(BackgroundStyle.HorizontalGrid),
    yAxisPaddingToLine: Dp = 8.dp,
    xAxisPaddingToBars: Dp = 2.dp,
    firstBarStartPadding: Dp = 0.dp,
    lastBarEndPadding: Dp = 0.dp,
    showTopLabelMisaligned: Boolean = false
) {

    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = modifier) {
        val horizontalLabelHeight = textMeasurer.measure(data[0].key.label).size.height
        val bottomOfChart = size.height - horizontalLabelHeight - xAxisPaddingToBars.toPx()
        val maxWidthOfVerticalLabels =
            verticalLabels.maxOf { textMeasurer.measure(it.label).size.width }.toFloat()

        drawVerticalLabelsAndBackground(
            textMeasurer = textMeasurer,
            verticalLabels = verticalLabels,
            labelStyle = labelStyle,
            bottomOfChart = bottomOfChart,
            background = background,
            axisStyle = axisStyle,
            yAxisInterval = yAxisInterval,
            yAxisPaddingToLine = yAxisPaddingToLine.toPx(),
            shouldShowTopLabelMisaligned = showTopLabelMisaligned,
            maxWidthOfVerticalLabels = maxWidthOfVerticalLabels
        )

        drawBarsWithHorizontalLabels(
            textMeasurer = textMeasurer,
            bars = data,
            barColor = barColor,
            yAxisInterval = yAxisInterval,
            bottomOfChart = bottomOfChart,
            paddingBetweenBars = spacingBetweenBars.toPx(),
            labelStyle = labelStyle,
            xAxisPaddingToBars = xAxisPaddingToBars.toPx(),
            firstBarStartPadding = firstBarStartPadding.toPx(),
            lastBarEndPadding = lastBarEndPadding.toPx(),
            yAxisPaddingToFirstBar = maxWidthOfVerticalLabels + yAxisPaddingToLine.toPx()
        )
    }
}

private fun DrawScope.drawBarsWithHorizontalLabels(
    textMeasurer: TextMeasurer,
    bars: List<Bar>,
    barColor: List<Color>,
    yAxisInterval: AxisInterval,
    bottomOfChart: Float,
    labelStyle: TextStyle,
    paddingBetweenBars: Float,
    yAxisPaddingToFirstBar: Float,
    xAxisPaddingToBars: Float,
    firstBarStartPadding: Float,
    lastBarEndPadding: Float
) {
    val canvasWidthWithoutPadding =
        size.width - yAxisPaddingToFirstBar - firstBarStartPadding - lastBarEndPadding
    val barWidth = (canvasWidthWithoutPadding - paddingBetweenBars * bars.lastIndex) / bars.size

    var barStartPosition = yAxisPaddingToFirstBar + firstBarStartPadding
    bars.forEachIndexed { index, bar ->
        val color = barColor.getOrNull(index) ?: barColor.first()
        val barHeightScaledToCanvas = bar.value * bottomOfChart / yAxisInterval.max
        drawBar(
            color = color,
            barStartPosition = barStartPosition,
            barHeightScaledToCanvas = barHeightScaledToCanvas,
            barVerticalPosition = bottomOfChart - barHeightScaledToCanvas,
            barWidth = barWidth
        )
        barStartPosition += barWidth + paddingBetweenBars
        drawHorizontalLabel(
            horizontalLabelSize = textMeasurer.measure(bar.key.label, labelStyle),
            barTopLeftPosition = barStartPosition,
            spacingBetweenBars = paddingBetweenBars,
            barWidth = barWidth,
            xAxisPaddingToBars = xAxisPaddingToBars
        )
    }
}

private fun DrawScope.drawBar(
    color: Color,
    barStartPosition: Float,
    barHeightScaledToCanvas: Float,
    barVerticalPosition: Float,
    barWidth: Float
) = drawRect(
    color = color,
    topLeft = Offset(
        x = barStartPosition,
        y = barVerticalPosition
    ),
    size = Size(
        width = barWidth,
        height = barHeightScaledToCanvas
    )
)

private fun DrawScope.drawHorizontalLabel(
    horizontalLabelSize: TextLayoutResult,
    barTopLeftPosition: Float,
    spacingBetweenBars: Float,
    barWidth: Float,
    xAxisPaddingToBars: Float
) {
    val barEndPosition = barTopLeftPosition - spacingBetweenBars
    val barStartPosition = barEndPosition - barWidth
    val halfPoint = (barEndPosition + barStartPosition) / 2
    val labelStartPosition = halfPoint - horizontalLabelSize.size.width / 2
    this.drawText(
        textLayoutResult = horizontalLabelSize,
        topLeft = Offset(
            labelStartPosition,
            size.height - xAxisPaddingToBars - horizontalLabelSize.size.height
        )
    )
}

private fun DrawScope.drawVerticalLabelsAndBackground(
    textMeasurer: TextMeasurer,
    verticalLabels: List<ValueLabel>,
    maxWidthOfVerticalLabels: Float,
    labelStyle: TextStyle,
    bottomOfChart: Float,
    background: List<BackgroundStyle>,
    axisStyle: AxisStyle,
    yAxisInterval: AxisInterval,
    yAxisPaddingToLine: Float,
    shouldShowTopLabelMisaligned: Boolean
) {
    background.firstOrNull { it is BackgroundStyle.Painter }?.let { style ->
        drawBackgroundGradient(
            brush = (style as BackgroundStyle.Painter).brush,
            startOffset = Offset(maxWidthOfVerticalLabels + yAxisPaddingToLine, 0f),
            endOffset = Offset(size.width, bottomOfChart)
        )
    }
    verticalLabels.forEachIndexed { index, label ->
        val labelSize = textMeasurer.measure(label.label, labelStyle)
        val halfOfLabelHeight = labelSize.size.height / 2
        val labelPositionScaledToCanvas = (bottomOfChart * label.yValue) / yAxisInterval.max
        val labelPosition = bottomOfChart - halfOfLabelHeight - labelPositionScaledToCanvas
        val isLastLabel = index == verticalLabels.lastIndex
        drawVerticalLabel(
            verticalLabelSize = labelSize,
            maxWidthOfVerticalLabel = maxWidthOfVerticalLabels,
            labelYPosition = if (isLastLabel && shouldShowTopLabelMisaligned) labelPosition + halfOfLabelHeight else labelPosition
        )
        if (background.contains(BackgroundStyle.HorizontalGrid)) {
            drawHorizontalBackgroundLines(
                labelYPosition = labelPosition,
                verticalLabelSize = labelSize,
                maxWidthOfVerticalLabel = maxWidthOfVerticalLabels,
                axisStyle = axisStyle,
                yAxisPaddingToLine = yAxisPaddingToLine
            )
        }
    }
}

private fun DrawScope.drawHorizontalBackgroundLines(
    labelYPosition: Float,
    verticalLabelSize: TextLayoutResult,
    maxWidthOfVerticalLabel: Float,
    axisStyle: AxisStyle,
    yAxisPaddingToLine: Float
) {
    val lineYPosition = labelYPosition + verticalLabelSize.size.height / 2
    val startLine = maxWidthOfVerticalLabel + yAxisPaddingToLine
    drawLine(
        brush = axisStyle.brush,
        start = Offset(startLine, lineYPosition),
        end = Offset(size.width, lineYPosition),
        strokeWidth = axisStyle.width.toPx()
    )
}

private fun DrawScope.drawVerticalLabel(
    verticalLabelSize: TextLayoutResult,
    maxWidthOfVerticalLabel: Float,
    labelYPosition: Float
) = drawText(
    textLayoutResult = verticalLabelSize,
    topLeft = Offset(maxWidthOfVerticalLabel - verticalLabelSize.size.width, labelYPosition)
)