package com.kevin.incomeexpence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kevin.incomeexpence.Fragments.AddDataFragment
import com.kevin.incomeexpence.Fragments.HomeFragment
import com.kevin.incomeexpence.Fragments.StatusFragment
import com.kevin.incomeexpence.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        binding.bottom.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.status -> replaceFragment(StatusFragment())
                R.id.add -> replaceFragment(AddDataFragment())

                else -> {

                }
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment) {

      supportFragmentManager.beginTransaction().replace(R.id.fragpageview,fragment).commit()
    }
}