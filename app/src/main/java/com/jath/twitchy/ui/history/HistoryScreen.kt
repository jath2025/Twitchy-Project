package com.jath.twitchy.ui.history
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jath.twitchy.data.HistoryRepository

@Composable
fun HistoryScreen(
    repo: HistoryRepository
) {
    val items = repo.getHistoryItems()

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

        if (repo.getHistoryItems().isEmpty()) {
            Text(
                text = "No history available",
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black
            )
        } else {
            for (item in repo.getHistoryItems()) {
                Text(item.toString())
            }
        }
    }
}