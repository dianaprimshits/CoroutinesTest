package com.diana.primshits.coroutine.core.coroutinesBindings

import android.view.View
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce

fun View.clicks(): Flow<Unit> = callbackFlow {
    setOnClickListener {
        offer(Unit)
    }
    awaitClose {
        setOnClickListener(null)
    }
}

fun View.filteredClicks(): Flow<Unit> = clicks().debounce(300)