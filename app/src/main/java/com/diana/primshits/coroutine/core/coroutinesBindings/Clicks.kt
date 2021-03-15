package com.diana.primshits.coroutine.core.coroutinesBindings

import android.view.View
import com.diana.primshits.coroutine.core.extentions.throttleFirst
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.sample

fun View.clicks(): Flow<Unit> = callbackFlow {
    setOnClickListener { offer(Unit) }
    awaitClose { setOnClickListener(null) }
}

fun View.debounceClicks(period: Long): Flow<Unit> = clicks().debounce(period)

fun View.throttleLastClicks(period: Long): Flow<Unit> = clicks().sample(period)

fun View.throttleFirstClicks(period: Long): Flow<Unit> = clicks().throttleFirst(period)