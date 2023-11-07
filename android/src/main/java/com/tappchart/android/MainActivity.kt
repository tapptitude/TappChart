package com.tappchart.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.tappchart.bar.BarChart
import com.tappchart.bar.model.Bar
import com.tapptitude.tappchart.HelloWorld
import com.tapptitude.tappchart.model.AxisInterval
import com.tapptitude.tappchart.model.DataLabel
import com.tapptitude.tappchart.model.ValueLabel
import com.tapptitude.tappchart.ui.AxisStyle

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BarChart(
                modifier = Modifier.fillMaxWidth().height(300.dp).padding(24.dp),
                yAxisInterval = AxisInterval(10f,0f),
                data = listOf(
                    Bar(DataLabel("Guest"),1.3f),
                    Bar(DataLabel("New User"),3f),
                    Bar(DataLabel("Existing User"),10f)
                ),
                verticalLabel = listOf(
                    ValueLabel("0",0f),
                    ValueLabel("2K",2f),
                    ValueLabel("4K",4f),
                    ValueLabel("6K",6f)
                ),
                labelStyle = TextStyle.Default,
                spacingBetweenBars = 16.dp,
                axisStyle = AxisStyle(1.dp, Brush.verticalGradient(listOf(Color.Gray, Color.Gray))),
                barColor = listOf(Color.Blue, Color.Red, Color.Yellow)
            )
        }
    }
}