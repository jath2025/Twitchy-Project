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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jath.twitchy.data.formatTime
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// lazyColumn and the items structure are inspired by Android Developers documentation:
// https://developer.android.com/develop/ui/compose/lists
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel
) {
    val summary = viewModel.summary
    var clearHistoryDialog by rememberSaveable() { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        if (summary.count != 0) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Fall summary (${summary.count} falls)",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, top = 8.dp))
                Text("Avg Accel: %.1f   Max Accel: %.1f"
                    .format(summary.avgAcceleration, summary.maxAcceleration),
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text("Avg Rota: %.1f    Max Rota: %.1f"
                    .format(summary.avgRotation, summary.maxRotation),
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )
            }
        }

        Text(
            text = "Fall History",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )

        LazyColumn (
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
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
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
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
                                IconButton(onClick = { viewModel.deleteFall(fall.id) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete fall")
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

        Button(onClick = { clearHistoryDialog = true }) {
            Text("Clear History")
        }

        // Structure for AlertDialog inspired by Android Developers documentation:
        // https://developer.android.com/develop/ui/compose/components/dialog
        if(clearHistoryDialog) {
            AlertDialog(
                title = { Text("Clear history?") },
                text = { Text("All saved logs will be deleted") },
                onDismissRequest = { clearHistoryDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.clearHistory()
                        clearHistoryDialog = false
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { clearHistoryDialog = false }) {
                        Text("Cancel")
                    }
                }

            )
        }

    }
}