package com.example.firstaiapp.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firstaiapp.databinding.ActivityMainBinding
import com.example.firstaiapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        binding.sendPromptBt.setOnClickListener {
            val prompt = binding.queryEt.text.toString()
            viewModel.generateContent(prompt)
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            responseText.observe(this@MainActivity) { responseText ->
                binding.modelResponseTv.text = responseText
            }

            isLoading.observe(this@MainActivity) { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }
}




