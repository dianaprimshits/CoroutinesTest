package com.diana.primshits.coroutine

import androidx.databinding.ObservableField

data class MainForm(
    val firstButton: ObservableField<String> = ObservableField("first"),
    val secondButton: ObservableField<String> = ObservableField("second")
)