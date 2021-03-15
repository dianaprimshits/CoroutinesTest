package com.diana.primshits.coroutine.core.extentions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

fun <T>Flow<T>.throttleFirst(period: Long): Flow<T> = flow {
    var lastEmitTime = 0L
    collect { value ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastEmitTime >= period) {
            lastEmitTime = currentTime
            emit(value)
        }
    }
}