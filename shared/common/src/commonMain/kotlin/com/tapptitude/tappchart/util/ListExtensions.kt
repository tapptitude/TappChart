package com.tapptitude.tappchart.util

fun <T> List<T>.fastForEachIndexed(action: (Int, T) -> Unit) {
    for (index in 0..this.lastIndex) action(index, this[index])
}