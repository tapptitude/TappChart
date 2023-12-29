package com.tappchart.radar

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tappchart.radar.model.NetStyle
import com.tappchart.radar.model.PolygonProperties
import com.tappchart.radar.model.RadarEntry
import com.tappchart.radar.model.RingsStyle

@ExperimentalTextApi
@Suppress("FunctionName")
@Preview
@Composable
fun Preview_RadarChart() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 60_000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        )
    )

    val entries = listOf(
        RadarEntry(
            values = listOf(.25f, .110f, .190f, .75f, .200f, .140f),
            PolygonProperties(
                borderColor = Color.Magenta,
                borderWidth = 3f,
                areaColor = Color.Magenta.copy(alpha = 0.3f),
                dotsRadius = 10f
            )
        ),
        RadarEntry(
            values = listOf(.250f, .310f, .90f, .150f, .20f, .400f),
            PolygonProperties(
                borderColor = Color.Cyan,
                borderWidth = 3f,
                areaColor = Color.Cyan.copy(alpha = 0.3f),
                dotsRadius = 10f
            )
        ),
        RadarEntry(
            values = listOf(.305f, .200f, .360f, .400f, .160f, .390f),
            PolygonProperties(
                borderColor = Color.Green,
                borderWidth = 3f,
                areaColor = Color.Green.copy(alpha = 0.3f),
                dotsRadius = 10f
            )
        ),
        RadarEntry(
            values = listOf(.100f, .350f, .30f, .175f, .267f, .440f),
            PolygonProperties(
                borderColor = Color.Red,
                borderWidth = 3f,
                areaColor = Color.Red.copy(alpha = 0.3f),
                dotsRadius = 10f
            )
        ),
        RadarEntry(
            values = listOf(.215f, .310f, .200f, .15f, .0f, .180f),
            PolygonProperties(
                borderColor = Color.Yellow,
                borderWidth = 3f,
                areaColor = Color.Yellow.copy(alpha = 0.3f),
                dotsRadius = 10f
            )
        ),
    )

    var netStyle by remember {
        mutableStateOf(
            NetStyle(
                width = 4f,
                color = Color.Black,
                ringsCount = 5,
                ringsDrawStyle = Stroke(),
                linesDrawStyle = Stroke(),
                ringsStyle = RingsStyle.ROUNDED,
                connectInCenter = false,
            )
        )
    }

    Column(
        Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxWidth().height(108.dp).border(1.dp, Color.Red)) {
            RingStyleColumn(
                modifier = Modifier,
                selectedRingStyle = netStyle.ringsStyle,
                onNetStyleSelected = { rs -> netStyle = netStyle.copy(ringsStyle = rs) }
            )
            ConnectInCenterColumn(
                Modifier,
                connectInCenter = netStyle.connectInCenter,
                onOptionChange = { netStyle = netStyle.copy(connectInCenter = it) },
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            RadarChart(
                modifier = Modifier.fillMaxSize(),
                pointsCount = 6,
                angleStartOffset = rotation,
                entries = entries,
                labels = listOf("label 1", "label 2", "label 3", "label 4", "label 5", "label 6"),
                labelStyle = TextStyle.Default.copy(fontStyle = FontStyle.Italic),
                netStyle = netStyle,
            )
        }

    }
}

@Composable
private fun OptionColumn(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    val scrollable = rememberScrollState()
    Column(
        modifier = modifier.then(
            Modifier.verticalScroll(scrollable)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.W600,
        )
        content()
    }
}

@Composable
private fun ConnectInCenterColumn(
    modifier: Modifier,
    connectInCenter: Boolean,
    onOptionChange: (Boolean) -> Unit,
) {
    OptionColumn(
        modifier = modifier,
        title = "Connect in center"
    ) {
        Button(enabled = !connectInCenter, onClick = { onOptionChange(true) }) {
            Text("Yes")
        }
        Button(enabled = connectInCenter, onClick = { onOptionChange(false) }) {
            Text("No")
        }
    }
}

@Composable
private fun RingStyleColumn(
    modifier: Modifier = Modifier,
    selectedRingStyle: RingsStyle,
    onNetStyleSelected: (RingsStyle) -> Unit,
) {
    OptionColumn(
        modifier = modifier,
        title = "Ring style"
    ) {
        RingsStyle.entries.forEach { rs ->
            Button(
                enabled = selectedRingStyle != rs,
                onClick = { onNetStyleSelected(rs) }
            ) {
                Text(text = rs.name)
            }
        }
    }
}