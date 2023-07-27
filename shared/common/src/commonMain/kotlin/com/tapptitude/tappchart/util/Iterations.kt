package com.tapptitude.tappchart.util

inline fun <T> List<T>.quickForEach(
    crossinline func: (T) -> Unit,
) {
    var i = 0
    while (i < this.size) {
        func(this[i])
        i++
    }
}