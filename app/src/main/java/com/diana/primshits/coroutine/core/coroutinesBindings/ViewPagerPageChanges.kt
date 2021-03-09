package com.diana.primshits.coroutine.core.coroutinesBindings

import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun ViewPager2.pageChanges(): Flow<Int> = callbackFlow {
    val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {

        override fun onPageSelected(position: Int) {
            offer(position)
        }
    }
    registerOnPageChangeCallback(pageChangeCallback)
    awaitClose { unregisterOnPageChangeCallback(pageChangeCallback) }
}