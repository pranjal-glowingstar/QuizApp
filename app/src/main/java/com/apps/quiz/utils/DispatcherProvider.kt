package com.apps.quiz.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatcherProvider {

    private val IO: CoroutineDispatcher = Dispatchers.IO

    fun getIODispatcher(): CoroutineDispatcher {
        return IO
    }
}