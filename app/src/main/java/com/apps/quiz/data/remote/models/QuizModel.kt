package com.apps.quiz.data.remote.models

import com.google.gson.annotations.SerializedName

data class QuizModel(
    @SerializedName("id") val id: Int,
    @SerializedName("question") val question: String,
    @SerializedName("options") val options: List<String>,
    @SerializedName("correctOptionIndex") val correctOptionIndex: Int
)
