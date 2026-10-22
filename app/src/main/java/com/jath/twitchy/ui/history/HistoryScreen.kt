package com.jath.twitchy.ui.history
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jath.twitchy.data.formatTime
import com.jath.twitchy.data.historydata.HistoryItem
import com.jath.twitchy.ui.theme.TwitchyBlueLight
import com.jath.twitchy.ui.theme.TwitchyCard
import com.jath.twitchy.ui.theme.appButtonColors
import com.jath.twitchy.ui.theme.appButtonColorsDanger

// lazyColumn and the items structure are inspired by Android Developers documentation:
// https://developer.android.com/develop/ui/compose/lists
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel
) {
    val summary = viewModel.summary
    var clearHistoryDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        if (summary.count != 0) {
            Card(
                colors = CardDefaults.cardColors(containerColor = TwitchyBlueLight, contentColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    "Fall summary (${summary.count} falls)",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, top = 8.dp)
                )
                Text(
                    "Max Acceleration: %.1f m/s²    Max Rotation: %.1f rad/s"
                        .format(summary.maxAcceleration, summary.maxRotation),
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    "Avg Acceleration: %.1f m/s²    Avg Rotation: %.1f rad/s"
                        .format(summary.avgAcceleration, summary.avgRotation),
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )
            }
        }

        Text(
            text = "Fall History",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Box(modifier = Modifier.weight(1f)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp)
            ) {
                if (viewModel.items.isEmpty()) {
                    item { Text("No history", fontSize = 18.sp, color = Color.White) }
                } else {
                    for (severity in listOf("High", "Medium", "Low")) {
                        val falls = viewModel.fallsBySeverity(severity)
                        if (falls.isNotEmpty()) {
                            item {
                                Text(
                                    "$severity (${falls.size})",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            items(falls, key = { it.id }) { fall ->
                                FallCard(fall, viewModel)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
            ) {
                Button(onClick = { viewModel.addFall() }, colors = appButtonColors()) { Text("Add Fall") } // Only for Demos, remove for final version
                Button(onClick = { clearHistoryDialog = true }, colors = appButtonColorsDanger()) { Text("Clear History") }
            }
        }

        // Structure for AlertDialog inspired by Android Developers documentation:
        // https://developer.android.com/develop/ui/compose/components/dialog
        if (clearHistoryDialog) {
            AlertDialog(
                title = { Text("Clear history?") },
                text = { Text("All saved logs will be deleted") },
                onDismissRequest = { clearHistoryDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.clearHistory()
                        clearHistoryDialog = false
                    }, colors = appButtonColorsDanger()) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { clearHistoryDialog = false }, colors = appButtonColors()) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
fun FallCard(fall: HistoryItem, viewModel: HistoryViewModel) {
    var deleteFallDialog by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = TwitchyCard, contentColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (
                modifier = Modifier.padding(8.dp)
            ) {
                Text(formatTime(fall.timestamp.toLong()))
                Text("Acceleration %.1f m/s²  Rotation %.1f rad/s".format(fall.acceleration, fall.rotation))
                Text("Height %.1f m".format(fall.altitude))
            }
            IconButton( onClick = { deleteFallDialog = true } ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete fall")
            }
        }
    }
    // Structure for AlertDialog inspired by Android Developers documentation:
    // https://developer.android.com/develop/ui/compose/components/dialog
    if (deleteFallDialog) {
        AlertDialog(
            title = { Text("Delete fall?") },
            text = { Text("The fall will be deleted, and cannot be restored!") },
            onDismissRequest = { deleteFallDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteFall(fall.id)
                    deleteFallDialog = false
                }, colors = appButtonColorsDanger()) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { deleteFallDialog = false }, colors = appButtonColors()) {
                    Text("Cancel")
                }
            }
        )
    }
}