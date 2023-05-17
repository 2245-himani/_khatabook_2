package com.kevin.incomeexpence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kevin.incomeexpence.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var fragments = arrayOf(HomeFragment(), StatusFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottom.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.status -> replaceFragment(StatusFragment())

                else -> {

                }
            }
            true
        }

    }
    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.viewpager, fragment)
        fragmentTransaction.commit()
    }
}