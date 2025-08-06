package com.apps.quiz.presentation.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.apps.quiz.R

@Composable
fun Streak(streak: Int, currentQuestion: Int, totalQuestions: Int){
    val context = LocalContext.current
    var animate by remember { mutableStateOf(false) }
    val scale: Float by animateFloatAsState(
        targetValue = if (animate) 1.5f else 1f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(streak) {
        if (streak >= 3) {
            animate = true
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth().height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "${context.getString(R.string.streak)} $streak")
        if(streak >= 3){
            Icon(imageVector = Icons.Filled.Star, contentDescription = "", modifier = Modifier.size(24.dp*scale), tint = Color(0xffffbf00))
            LaunchedEffect(scale) {
                if (scale == 1.5f) {
                    animate = false
                }
            }
        }
    }
    Text(text = "Question $currentQuestion of $totalQuestions", modifier = Modifier.padding(top = 12.dp))
    LinearProgressIndicator(
        color = Color.Green,
        trackColor = Color.Gray,
        progress = {
            currentQuestion.toFloat() / totalQuestions.toFloat()
        },
        modifier = Modifier.padding(top = 12.dp)
    )
}