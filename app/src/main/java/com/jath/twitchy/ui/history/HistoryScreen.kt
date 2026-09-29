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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jath.twitchy.data.fallSeverity

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = viewModel()
) {
    val items = viewModel.items

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
            if (items.isEmpty()) {
                item {
                    Text("No history")
                }
            } else {
                items(items.size) { index ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val item = items[index]

                        Text(
                            "Fall id: ${item.id}, " +
                            "Severity: ${fallSeverity(item.acceleration)}, " +
                            "Timestamp: ${item.timestamp}, " +
                            "Acceleration: ${item.acceleration}, " +
                            "Rotation: ${item.rotation}, " +
                            "Altitude: ${item.altitude}",
                            modifier = Modifier.weight(1f)
                        )
                        Button(onClick = { viewModel.deleteFall(item.id) }) {
                            Text("Delete Fall")
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