package com.tappchart.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import com.tappchart.radar.Preview_RadarChart
import com.tapptitude.tappchart.HelloWorld

@ExperimentalTextApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Preview_RadarChart()
        }
    }
}