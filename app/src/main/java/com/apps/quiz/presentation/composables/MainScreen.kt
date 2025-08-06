package com.apps.quiz.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.apps.quiz.R
import com.apps.quiz.presentation.viewmodel.MainViewModel
import com.apps.quiz.presentation.viewmodel.QuizUiState

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val quizQuestion by viewModel.quizQuestion.collectAsStateWithLifecycle()
    val streak by viewModel.streak.collectAsStateWithLifecycle()
    val optionModel by viewModel.optionModel.collectAsStateWithLifecycle()
    val longestStreak by viewModel.longestStreak.collectAsStateWithLifecycle()
    val correctAnswers by viewModel.correctAnswers.collectAsStateWithLifecycle()
    val shouldWaitForNextQuestion by viewModel.shouldWaitForNextQuestion.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getRandomQuizQuestions()
    }

    val onOptionClicked: (Int, Int) -> Unit = remember(viewModel) {
        { selected, correct ->
            viewModel.onOptionClicked(selected, correct)
        }
    }
    val onRestart = remember(viewModel) {
        {
            viewModel.resetStatesAndRestartQuiz()
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(color = Color.DarkGray)) {
        when (uiState) {
            QuizUiState.Loader -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { CircularProgressIndicator(color = Color.Green, modifier = Modifier.size(48.dp)) }
            }

            QuizUiState.Error -> {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) { Text(text = context.getString(R.string.error), textAlign = TextAlign.Center) }
            }

            QuizUiState.Result -> {
                ResultScreen(
                    longestStreak,
                    correctAnswers,
                    viewModel.getTotalQuestions(),
                    onRestart
                )
            }

            QuizUiState.Question -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 16.dp, horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Streak(streak, viewModel.getCurrentQuestion(), viewModel.getTotalQuestions())
                    quizQuestion?.let { ques ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = ques.question,
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            ques.options.forEachIndexed { index, option ->
                                Option(
                                    index,
                                    option,
                                    onOptionClicked,
                                    ques.correctOptionIndex,
                                    optionModel.optionList[index]
                                )
                            }
                            if(shouldWaitForNextQuestion){
                                Text(
                                    text = context.getString(R.string.wait),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(top = 12.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { viewModel.moveToNextQuestionOrShowResult() }, modifier = Modifier
                            .height(36.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = context.getString(R.string.skip)
                        )
                    }
                }
            }

            QuizUiState.None -> {
                //do nothing
            }
        }
    }

}