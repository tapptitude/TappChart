package com.tappchart.bar.model

import com.tapptitude.tappchart.model.DataLabel
import java.util.Objects

class Bar(
    val key: DataLabel,
    val value: Float,
) {
    override fun toString(): String = "Bar(key=$key, value=$value)"
    override fun equals(other: Any?): Boolean = other is Bar && other.key == key && other.value == value
    override fun hashCode(): Int = Objects.hash(key, value)
}