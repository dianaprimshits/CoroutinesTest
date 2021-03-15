package com.diana.primshits.coroutine.core.coroutinesBindings

import android.widget.CompoundButton
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

fun CompoundButton.stateChanges() = callbackFlow {
    setOnCheckedChangeListener { _, isChecked -> offer(isChecked) }
    awaitClose { setOnCheckedChangeListener(null) }
}