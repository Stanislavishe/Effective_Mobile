package com.example.effectivemobile.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        navController = navHostFragment.navController

        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_search -> navController.navigate(R.id.navigation_search)
                R.id.navigation_favorites -> navController.navigate(R.id.navigation_favorites)
                R.id.navigation_responses -> navController.navigate(R.id.navigation_responses)
            }
            true
        }


    }
}