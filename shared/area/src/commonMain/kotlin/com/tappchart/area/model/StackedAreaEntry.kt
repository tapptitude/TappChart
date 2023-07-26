package com.tappchart.area.model

import com.tapptitude.tappchart.model.DataLabel

data class StackedAreaEntry(
    val label: DataLabel,
    val entries: List<ChartPoint>
)
