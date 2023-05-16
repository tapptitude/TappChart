package com.tappchart.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.tappchart.circle.PieChart
import com.tappchart.circle.model.LabelPosition
import com.tappchart.circle.model.Slice

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PieChart(
                modifier = Modifier.width(400.dp).height(400.dp),
                slices = listOf(
                    Slice("1", 26F, LabelPosition.INSIDE, Color.Blue),
                    Slice("2", 32F, LabelPosition.INSIDE, Color.Green),
                    Slice("3", 24F, LabelPosition.INSIDE, Color.Yellow),
                    Slice("4", 10F, LabelPosition.INSIDE, Color.Red),
                    Slice("5", 9F, LabelPosition.INSIDE, Color.Magenta)
                ),
                angleOffset = 0F,
                marginBetweenSlices = 4.dp,
                labelStyle = TextStyle()
            )
        }
    }
}