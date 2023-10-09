package com.tappchart.radar.model

/**
 * Class holding the data that will be used to draw on the screen
 * The values must match the number of corners the [RadarChart].
 * If the number of entries in the [values] is smaller than the number of corners,
 * the rest will be considered to be 0
 */
data class RadarEntry(
    val values: List<Float>,
    val polygon: PolygonProperties
) {

    fun valueAt(position: Int): Float {
        return if (position < 0 || position > values.size) {
            0f
        } else {
            values[position]
        }
    }
}