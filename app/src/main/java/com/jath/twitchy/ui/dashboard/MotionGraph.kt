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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
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
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
    ) {
        if (history.size < 2) return@Canvas

        val spacingX = size.width / (history.size - 1)
        val range = (maxValue - minValue).coerceAtLeast(0.001f)

        for (i in 1 until history.size) {
            val normalizedPrev = (history[i - 1] - minValue) / range
            val normalizedCurr = (history[i] - minValue) / range

            val x1 = spacingX * (i - 1)
            val y1 = (size.height - normalizedPrev * size.height).coerceIn(0f, size.height)
            val x2 = spacingX * i
            val y2 = (size.height - normalizedCurr * size.height).coerceIn(0f, size.height)

            drawLine(
                color = graphColor,
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = 4f
            )
        }
    }
}