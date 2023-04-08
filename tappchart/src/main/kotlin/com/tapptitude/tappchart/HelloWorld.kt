package com.tapptitude.tappchart

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Welcomes the user to our library.
 *
 * @param modifier [Modifier]
 * @param platform the platform you're calling this function from.
 * We avoid using the expect/actual mechanism, since we want a simple library
 */
@Composable
fun HelloWorld(
    modifier: Modifier = Modifier,
    platform: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BasicText(
            text = "Hello World!",
            style = TextStyle.Default.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.size(12.dp))
        BasicText(text = "From $platform with <3")
    }
}


@Preview
@Composable
private fun Preview_HelloWorld() {
    HelloWorld(
        modifier = Modifier.fillMaxSize(),
        platform = "Preview",
    )
}