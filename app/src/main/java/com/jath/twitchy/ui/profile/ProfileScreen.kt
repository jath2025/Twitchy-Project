package com.jath.twitchy.ui.profile
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    val profile by viewModel.profile.collectAsStateWithLifecycle()
    val isRecording by viewModel.isRecording.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text("Profile")

        OutlinedTextField(
            value = profile.name,
            onValueChange = { viewModel.updateName(it) },
            label = { Text("Name") }
        )
        Button(
            onClick = {
                viewModel.updateName(profile.name)
            }
        ) {
            Text("Save Name")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Baseline")
        Button(
            onClick = {
                viewModel.recordBaseline()
            },
            enabled = !isRecording
        ) {
            Text(if (isRecording) "Recording..." else "Record Baseline")
        }

        val baseline = profile.baseline
        if (baseline != null) {
            Text("Accel: %.2f, Rotation: %.2f, Height: %.2f ".format(
                baseline.acceleration,
                baseline.rotation,
                baseline.altitude)
            )
        } else {
            Text("No baseline recorded")
        }
    }
}