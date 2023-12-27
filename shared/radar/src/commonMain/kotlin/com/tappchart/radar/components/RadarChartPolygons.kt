package com.tappchart.radar.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.tappchart.radar.Constants
import com.tappchart.radar.math.PointCalculator
import com.tappchart.radar.model.RadarEntry

fun DrawScope.drawPolygon(
    pointsCount: Int,
    startAngleOffset: Float,
    entry: RadarEntry,
    circleRadius: Float
) {
    val degreesPerAngle: Double = Constants.CIRCLE_DEGREE / pointsCount
    val graphPath = Path()
    val points: MutableList<Offset> = mutableListOf()

    for (i in Constants.FIRST_ELEMENT_POSITION..pointsCount) {
        val angle = startAngleOffset + degreesPerAngle * i
        val entryValue = entry.valueAt(i - 1)

        val x = size.width / 2 + PointCalculator.xCoordinateOnCircle(angle = angle, radius = circleRadius) * entryValue
        val y = size.height / 2 + PointCalculator.yCoordinateOnCircle(angle = angle, radius = circleRadius) * entryValue

        if (i == Constants.FIRST_ELEMENT_POSITION) {
            graphPath.moveTo(x, y)
        } else {
            graphPath.lineTo(x, y)
        }
        points.add(Offset(x,y))
    }
    graphPath.close()

    entry.polygon.let {
        drawPath(path = graphPath, color = it.borderColor, style = Stroke(width = it.borderWidth))
        if (it.dotsRadius != 0f) {
            drawPoints(
                points = points,
                pointMode = PointMode.Points,
                color = it.borderColor,
                strokeWidth = it.dotsRadius,
                cap = StrokeCap.Round
            )
        }
        if (it.areaColor != Color.Unspecified) {
            drawPath(path = graphPath, color = it.areaColor)
        }
    }
}