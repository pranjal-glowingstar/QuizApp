package com.apps.quiz.di

import com.apps.quiz.data.remote.QuizApi
import com.apps.quiz.repository.remote.IQuizRepository
import com.apps.quiz.repository.remote.QuizRepositoryImpl
import com.apps.quiz.utils.AppConstants
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class QuizModule {

    @Provides
    @Singleton
    fun providesCurrencyApi(): QuizApi{
        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(QuizApi::class.java)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson{
        return Gson()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class QuizInterfaceModule{

    @Binds
    @Singleton
    abstract fun providesQuizRepository(quizRepositoryImpl: QuizRepositoryImpl): IQuizRepository
}