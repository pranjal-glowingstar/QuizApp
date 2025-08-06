package com.apps.quiz.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.apps.quiz.R

@Composable
fun ResultScreen(
    longestStreak: Int,
    correctAnswers: Int,
    totalQuestions: Int,
    onRestartClicked: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = context.getString(R.string.results))
        Spacer(modifier = Modifier.padding(top = 48.dp))
        Text(
            text = context.getString(R.string.congratulations),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = context.getString(R.string.summary),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .height(72.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(size = 8.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text(text = "${context.getString(R.string.correct)} $correctAnswers")
                    Text(text = "${context.getString(R.string.total)} $totalQuestions")
                }
            }
            Box(
                modifier = Modifier
                    .height(72.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(size = 8.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "${context.getString(R.string.longest_streak)} $longestStreak")
            }
        }
        Button(onClick = onRestartClicked) {
            Text(text = context.getString(R.string.restart))
        }
    }
}