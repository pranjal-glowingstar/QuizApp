package com.apps.quiz.repository.remote

import com.apps.quiz.data.remote.models.QuizModel
import retrofit2.Response

interface IQuizRepository {

    suspend fun getRandomQuizQuestions(): Response<List<QuizModel>>
}