package com.apps.quiz.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(longestStreak: Int, correctAnswers: Int, totalQuestions: Int, onRestartClicked: ()-> Unit) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Quiz Ended")
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
            Box(modifier = Modifier.height(72.dp), contentAlignment = Alignment.Center) {
                Column {
                    Text(text = "Correct Answers: $correctAnswers")
                    Text(text = "Total Questions: $totalQuestions")
                }
            }
            Box(modifier = Modifier.height(72.dp), contentAlignment = Alignment.Center) {
                Text(text = "Longest Streak: $longestStreak")
            }
        }
        Button(onClick = onRestartClicked) {
            Text(text = "Restart")
        }
    }
}