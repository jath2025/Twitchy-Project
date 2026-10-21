package com.jath.twitchy.ui.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

private const val MAX_HISTORY = 75

// The Canvas math / live-updating graph logic in MotionGraph was developed with assistance from (Claude, Anthropic).
// The parameters, const and modifiers were implemented by me.

@Composable
fun MotionGraph(
    newValue: Float,
    graphColor: Color,
    modifier: Modifier = Modifier,
    minValue: Float = 0f,
    maxValue: Float = 25f
) {
    val history = remember { mutableStateListOf<Float>() }

    LaunchedEffect(newValue) {
        history.add(newValue)
        if (history.size > MAX_HISTORY) history.removeAt(0)
    }

    Canvas(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFF2E4050)))
    {
        val path = Path()
        history.forEachIndexed { i, value ->
            val x = size.width * i / (MAX_HISTORY - 1)
            val fraction = ((value - minValue) / (maxValue - minValue)).coerceIn(0f, 1f)
            val y = size.height * (1 - fraction)
            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        drawPath(
            path,
            graphColor,
            style = Stroke(width = 4f)
        )
    }
}