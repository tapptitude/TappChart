package com.tappchart.bar.model

import com.tapptitude.tappchart.model.DataLabel

/**
 * The order of entries in [bars] is the order we draw it on the screen.
 */
data class BarEntrySet(
    val dataLabel: DataLabel,
    val bars: List<Bar>
)
