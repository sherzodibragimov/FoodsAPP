package com.example.foodsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.foodsapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment()
    }

    private fun setFragment() {
        val navHost=supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        val navGraph=navHost.navController.navInflater.inflate(R.navigation.main_navigation)
        navHost.navController.graph=navGraph
    }
}