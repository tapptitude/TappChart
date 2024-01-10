package com.tapptitude.tappchart.model

import java.util.Objects

class AxisInterval(
    val max: Float,
    val min: Float = 0f,
) {
    override fun toString(): String = "AxisInterval(max=$max, min=$min)"
    override fun equals(other: Any?): Boolean = other is AxisInterval && other.max == max && other.min == min
    override fun hashCode(): Int = Objects.hash(max, min)
}