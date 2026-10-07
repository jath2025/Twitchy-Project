package com.jath.twitchy.ui.history
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jath.twitchy.data.formatTime
import androidx.compose.foundation.lazy.items

// lazyColumn and the items structure are inspired by Android Developers documentation:
// https://developer.android.com/develop/ui/compose/lists
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Fall History",
            fontSize = 24.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn (
            modifier = Modifier.weight(1f)
        ) {
            if (viewModel.items.isEmpty()) {
                item { Text("No history", fontSize = 18.sp) }
            } else {
                for (severity in listOf("High", "Medium", "Low")) {
                    val falls = viewModel.fallsBySeverity(severity)
                    if (falls.isNotEmpty()) {
                        item {
                            Text(
                                "$severity (${falls.size})",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        items(falls, key = { it.id }) { fall ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                val currTime = fall.timestamp
                                Text(
                                    "Fall id: ${fall.id}, " +
                                    "Time: ${formatTime(currTime.toLong())}, " +
                                    "Acceleration: %.1f, ".format(fall.acceleration) +
                                    "Rotation: %.1f, ".format(fall.rotation) +
                                    "Height: %.1f".format(fall.altitude),
                                    modifier = Modifier.weight(1f)
                                )
                                Button(onClick = { viewModel.deleteFall(fall.id) }) {
                                    Text("Delete Fall")
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.addFall()
        }) {
            Text("Add Fall")
        }

        Button(onClick = { viewModel.clearHistory() }) {
            Text("Clear History")
        }
    }
}