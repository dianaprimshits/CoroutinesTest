package com.diana.primshits.coroutine

import androidx.lifecycle.ViewModel

class MainViewModel(
    val form: MainForm
) : ViewModel() {

    fun clickFirstButton() {
        form.firstButton.set("updated")
    }

    fun clickSecondButton() {
        form.secondButton.set("updated")
    }
}