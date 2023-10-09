package com.tappchart.radar.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.toSize
import com.tappchart.radar.Constants.CIRCLE_DEGREE
import com.tappchart.radar.math.PointCalculator
import com.tappchart.radar.math.inRadians
import com.tapptitude.tappchart.util.quickForEach
import kotlin.math.cos
import kotlin.math.sin

@ExperimentalTextApi
internal fun DrawScope.drawLabels(
    startAngleOffset: Double = 0.0,
    textMeasurer: TextMeasurer,
    labels: List<String>,
    labelStyle: TextStyle,
    radius: Float,
) {
    val degreesPerAngle = CIRCLE_DEGREE / labels.size
    var currentAngle = startAngleOffset

    labels.quickForEach {
        val textSize = textMeasurer.measure(it).size
        val x = size.width / 2 + PointCalculator.xCoordinateOnCircle(
            angle = currentAngle,
            radius = radius
        ) - textSize.width / 2 + (textSize.width * 0.7f * cos(currentAngle.inRadians))

        val y = size.height / 2 + PointCalculator.yCoordinateOnCircle(
            angle = currentAngle, radius = radius
        ) - textSize.height / 2 + (textSize.height * 0.7f * sin(currentAngle.inRadians))

        drawText(
            textMeasurer,
            it,
            topLeft = Offset(x = x.toFloat(), y = y.toFloat()),
            overflow = TextOverflow.Visible,
            style = labelStyle
        )

        currentAngle += degreesPerAngle
    }
}