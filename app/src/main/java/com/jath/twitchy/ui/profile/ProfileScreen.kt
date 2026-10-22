package com.jath.twitchy.ui.profile
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
                    onValueChange = { viewModel.updateName(it) },
                    label = { Text("Name", fontSize = 16.sp) },
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

                val baseline = profile.baseline
                if (baseline != null) {
                    Text("Current Baseline:", fontSize = 18.sp)
                    Text(
                        "Acceleration: %.2f \n".format(baseline.acceleration) +
                        "Rotation: %.2f \n".format(baseline.rotation) +
                        "Height: %.2f ".format(baseline.altitude),
                        fontSize = 16.sp
                        )
                } else {
                    Text("No baseline recorded")
                }
            }
        }
    }
}