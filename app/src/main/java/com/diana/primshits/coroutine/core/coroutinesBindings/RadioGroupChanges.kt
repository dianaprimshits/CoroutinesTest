package com.diana.primshits.coroutine.core.coroutinesBindings

import android.widget.RadioGroup
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

fun RadioGroup.stateChanges() = callbackFlow {
    val listener = object : RadioGroup.OnCheckedChangeListener {
        private var lastCheckedId = checkedRadioButtonId

        override fun onCheckedChanged(group: RadioGroup, id: Int) {
            if (id != lastCheckedId) {
                lastCheckedId = id
                offer(id)
            }
        }
    }

    setOnCheckedChangeListener(listener)
    awaitClose { setOnCheckedChangeListener(null) }
}