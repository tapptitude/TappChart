package com.tappchart.radar.math

/**
 * Returns [firstNumber] if [firstNumber] is equal or higher than [secondNumber]. Otherwise returns [secondNumber]
 */
inline fun <T> highestBetween(firstNumber: T, secondNumber: T): T where T : Number, T : Comparable<T> {
    return if (firstNumber >= secondNumber) firstNumber else secondNumber
}