package com.diana.primshits.coroutine.core.coroutinesBindings

import android.view.View
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun View.focusChanges(): Flow<Boolean> = callbackFlow {
    val focusChangeListener = View.OnFocusChangeListener { _, hasFocus -> offer(hasFocus) }

    onFocusChangeListener = focusChangeListener
    awaitClose { onFocusChangeListener = null }
}