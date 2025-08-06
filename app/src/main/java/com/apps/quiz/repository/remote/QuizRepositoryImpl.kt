package com.apps.quiz.repository.remote

import com.apps.quiz.data.remote.QuizApi
import com.apps.quiz.data.remote.models.QuizModel
import retrofit2.Response
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(private val quizApi: QuizApi):
    IQuizRepository {
    override suspend fun getRandomQuizQuestions(): Response<List<QuizModel>> {
        return quizApi.getQuizData()
    }
}