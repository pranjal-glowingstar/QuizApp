package com.apps.quiz.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.apps.quiz.presentation.models.OptionState

@Composable
fun Option(
    index: Int,
    option: String,
    onOptionClicked: (Int, Int) -> Unit,
    correctOptionIndex: Int,
    optionState: OptionState
) {
    var isOptionClicked by remember { mutableStateOf(false) }
    LaunchedEffect(index, option) {
        isOptionClicked = false
    }
    Box(
        modifier = Modifier
            .height(36.dp)
            .padding(horizontal = 12.dp, vertical = 2.dp)
            .clickable(enabled = !isOptionClicked) {
                isOptionClicked = true
                onOptionClicked(
                    index,
                    correctOptionIndex
                )
            }
            .background(
                color = when (optionState) {
                    OptionState.CORRECT -> Color.Green
                    OptionState.INCORRECT -> Color.Red
                    OptionState.UNSELECTED -> Color.Gray
                },
                shape = RoundedCornerShape(size = 12.dp)
            )
            .fillMaxWidth(),
        contentAlignment = Alignment.Center) {
        Text(
            text = option,
            textAlign = TextAlign.Center
        )
    }
}