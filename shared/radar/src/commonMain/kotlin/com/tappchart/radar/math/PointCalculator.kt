package com.tappchart.radar.math

import androidx.compose.ui.geometry.Offset
import kotlin.math.cos
import kotlin.math.sin

internal object PointCalculator {

    /**
     * Returns
     */
    fun pointsAroundCircle(
        pointsCount: Int,
        circleRadius: Float,
        radiusOffset: Float = 0f,
    ): List<Offset> = buildList {
        val degreesPerAngle = 360 / pointsCount
        var i = 1
        while (i <= pointsCount) {
            val angle = 1.0 * degreesPerAngle * i + radiusOffset
            val x = xCoordinateOnCircle(angle = angle, radius = circleRadius)
            val y = yCoordinateOnCircle(angle = angle, radius = circleRadius)
            add(Offset(x = x, y = y))
            i++
        }
    }


    /**
     * Returns the X coordinate, starting from 3 o'clock counter-clockwise, of the Point determined
     * by the [angle] and [radius]
     *
     * @param angle - the angle of the point, in __radians__.
     * @param radius - the radius of the circle.
     */
    fun xCoordinateOnCircle(
        angle: Double,
        radius: Float,
    ): Float = (cos(angle.inRadians) * radius).toFloat()

    /**
     * Returns the X coordinate, starting from 3 o'clock counter-clockwise, of the Point determined
     * by the [angle] and [radius]
     *
     * @param angle - the angle of the point, in __radians__.
     * @param radius - the radius of the circle.
     */
    fun yCoordinateOnCircle(
        angle: Double,
        radius: Float,
    ): Float = (sin(angle.inRadians) * radius).toFloat()
}