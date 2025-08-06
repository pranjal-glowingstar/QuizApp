package com.apps.quiz.presentation.models

data class OptionModel(val optionList: List<OptionState> = mutableListOf(OptionState.UNSELECTED, OptionState.UNSELECTED, OptionState.UNSELECTED, OptionState.UNSELECTED))

enum class OptionState {
    CORRECT,
    INCORRECT,
    UNSELECTED
}
