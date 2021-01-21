package com.diana.primshits.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.diana.primshits.coroutine.core.extentions.getViewModel
import com.diana.primshits.coroutine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel = getViewModel { MainViewModel(MainForm()) }

        val binding = ActivityMainBinding.inflate(layoutInflater).apply {
            form = viewModel.form
            firstBt.setOnClickListener { viewModel.clickFirstButton() }
            secondBt.setOnClickListener { viewModel.clickSecondButton() }
        }
        setContentView(binding.root)
    }
}