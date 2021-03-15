package com.diana.primshits.coroutine.core.coroutinesBindings

import android.widget.SearchView
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

fun SearchView.queryTextChanges(isCustomHandleNeeded: Boolean): Flow<CharSequence> = callbackFlow {
    val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean = isCustomHandleNeeded

        override fun onQueryTextChange(newText: String): Boolean {
            offer(newText)
            return isCustomHandleNeeded
        }
    }
    setOnQueryTextListener(queryTextListener)
    awaitClose { setOnQueryTextListener(null) }
}.conflate()