package com.diana.primshits.coroutine.core.coroutinesBindings

import android.text.Editable
import android.text.TextWatcher
import android.widget.SearchView
import android.widget.TextView
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun TextView.textChanges(): Flow<CharSequence?> = callbackFlow {
    val textChangedListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            offer(s)
        }
    }
    addTextChangedListener(textChangedListener)
    awaitClose { removeTextChangedListener(textChangedListener) }
}