package com.hermannsterling.jobsearchapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.hermannsterling.jobsearchapp.R
import com.hermannsterling.jobsearchapp.databinding.ActivityMainBinding
import com.hermannsterling.jobsearchapp.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getJobs()
    }
}