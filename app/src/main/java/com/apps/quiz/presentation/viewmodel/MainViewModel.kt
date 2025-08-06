package com.apps.quiz.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.quiz.utils.DispatcherProvider
import com.apps.quiz.data.remote.models.QuizModel
import com.apps.quiz.presentation.models.OptionModel
import com.apps.quiz.presentation.models.OptionState
import com.apps.quiz.repository.remote.IQuizRepository
import com.apps.quiz.utils.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.max

@HiltViewModel
class MainViewModel @Inject constructor(private val quizRepository: IQuizRepository) : ViewModel() {

    private var quizList = mutableListOf<QuizModel>()
    private var currentQuestion = 0

    private val _quizQuestion: MutableStateFlow<QuizModel?> = MutableStateFlow(null)
    private val _streak = MutableStateFlow(0)
    private val _longestStreak = MutableStateFlow(0)
    private val _optionState = MutableStateFlow(OptionModel())
    private val _correctAnswers = MutableStateFlow(0)
    private val _uiState: MutableStateFlow<QuizUiState> = MutableStateFlow(QuizUiState.None)


    val quizQuestion = _quizQuestion.asStateFlow()
    val streak = _streak.asStateFlow()
    val longestStreak = _longestStreak.asStateFlow()
    val optionModel = _optionState.asStateFlow()
    val correctAnswers = _correctAnswers.asStateFlow()
    val uiState = _uiState.asStateFlow()

    fun getRandomQuizQuestions() {
        viewModelScope.launch(DispatcherProvider.getIODispatcher()) {
            _uiState.value = QuizUiState.Loader
            try {
                val response = quizRepository.getRandomQuizQuestions()
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        quizList = body as MutableList<QuizModel>
                        currentQuestion = 0
                        _quizQuestion.value = quizList[currentQuestion]
                    } ?: run{
                        _uiState.value = QuizUiState.Error
                    }
                }
            }catch (e: Exception){
                Log.d("Exception in network call", e.message ?: "")
                _uiState.value = QuizUiState.Error
            }
            _uiState.value = QuizUiState.InProgress
        }
    }
    fun onOptionClicked(selectedOption: Int, correctOption: Int){
        if(selectedOption == correctOption){
            _streak.value++
            _correctAnswers.value++
            _longestStreak.value = max(_longestStreak.value, _streak.value)
            val currentOptionList = _optionState.value.optionList.toMutableList()
            currentOptionList[selectedOption] = OptionState.CORRECT
            _optionState.value = _optionState.value.copy(optionList = currentOptionList)
        }else{
            _streak.value = 0
            val currentOptionList = _optionState.value.optionList.toMutableList()
            currentOptionList[correctOption] = OptionState.CORRECT
            currentOptionList[selectedOption] = OptionState.INCORRECT
            _optionState.value = _optionState.value.copy(optionList = currentOptionList)
        }
        viewModelScope.launch(DispatcherProvider.getIODispatcher()) {
            delay(AppConstants.DELAY_BETWEEN_QUESTIONS)
            moveToNextQuestionOrShowResult()
        }
    }
    fun getTotalQuestions() = quizList.size
    fun getCurrentQuestion() = currentQuestion + 1
    fun resetStatesAndRestartQuiz(){
        _streak.value = 0
        _longestStreak.value = 0
        _optionState.value = OptionModel()
        _correctAnswers.value = 0
        _quizQuestion.value = null
        getRandomQuizQuestions()
    }
    fun moveToNextQuestionOrShowResult(){
        if(quizList.size <= currentQuestion + 1){
            _uiState.value = QuizUiState.Result
        }else{
            currentQuestion++
            _optionState.value = OptionModel()
            _quizQuestion.value = quizList[currentQuestion]
        }
    }
}
sealed class QuizUiState{
    data object Loader: QuizUiState()
    data object Error: QuizUiState()
    data object Result: QuizUiState()
    data object InProgress: QuizUiState()
    data object None: QuizUiState()
}