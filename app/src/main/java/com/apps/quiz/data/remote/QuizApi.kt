package com.apps.quiz.data.remote

import com.apps.quiz.data.remote.models.QuizModel
import retrofit2.Response
import retrofit2.http.GET

interface QuizApi {

    @GET("/dr-samrat/53846277a8fcb034e482906ccc0d12b2/raw")
    suspend fun getQuizData(): Response<List<QuizModel>>
}