package com.tappchart.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tappchart.bar.BarChart
import com.tappchart.bar.model.Bar
import com.tapptitude.tappchart.model.AxisInterval
import com.tapptitude.tappchart.model.DataLabel
import com.tapptitude.tappchart.model.ValueLabel
import com.tapptitude.tappchart.ui.AxisStyle

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
//            BarChart(
//                modifier = Modifier.fillMaxWidth().height(300.dp).padding(24.dp),
//                yAxisInterval = AxisInterval(8f,0f),
//                data = listOf(
//                    Bar(DataLabel("Guest"),1.3f),
//                    Bar(DataLabel("New User"),3f),
//                    Bar(DataLabel("Existing User"),8f)
//                ),
//                verticalLabel = listOf(
//                    ValueLabel("0",0f),
//                    ValueLabel("2K",2f),
//                    ValueLabel("4K",4f),
//                    ValueLabel("6K",6f)
//                ),
//                labelStyle = TextStyle.Default,
//                spacingBetweenBars = 16.dp,
//                axisStyle = AxisStyle(1.dp, Brush.verticalGradient(listOf(Color.Gray, Color.Gray))),
//                barColor = listOf(Color.Blue, Color.Blue, Color.Blue)
//            )

            BarChart(
                modifier = Modifier.fillMaxWidth().height(300.dp).padding(24.dp),
                yAxisInterval = AxisInterval(28f,0f),
                data = listOf(
                    Bar(DataLabel("Apr 2020"),22f),
                    Bar(DataLabel("May 2020"),21.5f),
                    Bar(DataLabel("Jun 2020"),23.5f),
                    Bar(DataLabel("Jul 2020"),26f),
                    Bar(DataLabel("Aug 2020"),22f),
                    Bar(DataLabel("Sep 2020"),21.5f)
                ),
                verticalLabel = listOf(
                    ValueLabel("0",0f),
                    ValueLabel("8K",8f),
                    ValueLabel("16K",16f),
                    ValueLabel("24K",24f)
                ),
                labelStyle = TextStyle.Default.copy(fontSize = 10.sp),
                spacingBetweenBars = 8.dp,
                axisStyle = AxisStyle(1.dp, Brush.verticalGradient(listOf(Color.Gray, Color.Gray))),
                barColor = listOf(Color.Blue),
                yAxisPaddingToFirstBar = 32.dp,
            )
        }
    }
}