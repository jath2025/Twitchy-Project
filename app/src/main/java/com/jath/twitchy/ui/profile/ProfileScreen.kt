package com.jath.twitchy.ui.profile
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Profile")



        Button(onClick = {
            // todo
        }) {
            Text("Record Baseline")
        }

        Text("No baseline recorded")
    }
}