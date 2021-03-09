package com.diana.primshits.coroutine.core.coroutinesBindings

import android.widget.SearchView
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun SearchView.queryTextChanges(): Flow<CharSequence?> = callbackFlow {
    val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean = false

        override fun onQueryTextChange(newText: String?): Boolean {
            offer(newText)
            return false
        }
    }
    setOnQueryTextListener(queryTextListener)
    awaitClose { setOnQueryTextListener(null) }
}