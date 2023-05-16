package com.kevin.incomeexpence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kevin.incomeexpence.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var fragments = arrayOf(HomeFragment(), StatusFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }   
}