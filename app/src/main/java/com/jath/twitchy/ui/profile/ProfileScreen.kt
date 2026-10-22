package com.jath.twitchy.ui.profile
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jath.twitchy.ui.theme.TwitchyBlue
import com.jath.twitchy.ui.theme.appButtonColors

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    val profile by viewModel.profile.collectAsStateWithLifecycle()
    val isRecording by viewModel.isRecording.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current // to hide keyboard and unfocus name field

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = TwitchyBlue, contentColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Profile",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = profile.name,
                    onValueChange = { viewModel.updateName(it.take(12)) },
                    label = { if (profile.name.length < 12) {
                        Text("Name", fontSize = 16.sp)
                    } else {
                        Text("Name (max 12 characters)", fontSize = 16.sp)
                    }
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                        cursorColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
                Button(
                    onClick = {
                        viewModel.updateName(profile.name)
                        focusManager.clearFocus()
                    },
                    colors = appButtonColors()
                ) {
                    Text("Save Name")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = TwitchyBlue, contentColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Baseline", fontSize = 24.sp)
                Button(
                    onClick = {
                        viewModel.recordBaseline()
                    },
                    enabled = !isRecording,
                    colors = appButtonColors()
                ) {
                    Text(if (isRecording) "Recording..." else "Record Baseline")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = TwitchyBlue, contentColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val baseline = profile.baseline
                if (baseline != null) {
                    Text("Current Baseline", fontSize = 24.sp)
                    CurrentBaseline("Acceleration", "%.1f m/s²".format(baseline.acceleration))
                    CurrentBaseline("Rotation", "%.1f rad/s".format(baseline.rotation))
                    CurrentBaseline("Height", "%.1f m".format(baseline.altitude))
                } else {
                    Text("No baseline recorded", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
private fun CurrentBaseline(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontSize = 16.sp)
        Text(value, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}