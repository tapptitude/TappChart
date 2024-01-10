package com.tapptitude.tappchart.model

import java.util.Objects

class ValueLabel(
    val label: String,
    val yValue: Float
) {
    override fun toString(): String = "ValueLabel(label=$label, yValue=$yValue)"
    override fun equals(other: Any?): Boolean = other is ValueLabel && other.label == label && other.yValue == yValue
    override fun hashCode(): Int = Objects.hash(label, yValue)
}