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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jath.twitchy.data.HistoryRepository
import com.jath.twitchy.data.mock.MockDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

// The Android Developers documentation was used for the
// rememberCoroutineScope launching a courtine from a button
// Source: https://developer.android.com/develop/ui/compose/side-effects

@Composable
fun HistoryScreen(
    repo: HistoryRepository
) {
    val items = repo.getHistoryItems()
    val dataSource = remember { MockDataSource() }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "History",
            fontSize = 24.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

    LazyColumn(
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
                        "Timestamp: ${item.timestamp}, " +
                        "Acceleration: ${item.acceleration}, " +
                        "Rotation: ${item.rotation}, " +
                        "Altitude: ${item.altitude}",
                        modifier = Modifier.weight(1f)
                    )
                    Button(onClick = { repo.deleteHistoryItem(item.id) }) {
                        Text("Delete Fall")
                    }
                }
            }
        }
    }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                val data = dataSource.getMockData().first()
                repo.createHistoryItem(
                    data.timestamp.toString(),
                    data.totalAcceleration(),
                    data.totalRotation(),
                    data.altitude
                )
            }
        }) {
            Text("Add Fall")
        }

        Button(onClick = { repo.clearHistory() }) {
            Text("Clear History")
        }
    }
}