package com.example.myapplication.data.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.data.presentation.ui.fragments.AboutFragment
import com.example.myapplication.data.presentation.ui.fragments.BreedsFragment
import com.example.myapplication.data.presentation.ui.fragments.FavouritesFragment
import com.example.myapplication.data.presentation.ui.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {

    private val homeFragment by lazy { HomeFragment() }
    private val breedsFragment by lazy { BreedsFragment() }
    private val favouritesFragment by lazy { FavouritesFragment() }
    private val aboutFragment by lazy { AboutFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(homeFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.home -> {
                    loadFragment(homeFragment)
                    true
                }

                R.id.breeds -> {
                    loadFragment(breedsFragment)
                    true
                }

                R.id.favourites -> {
                    loadFragment(favouritesFragment)
                    true
                }

                R.id.about -> {
                    loadFragment(aboutFragment)
                    true
                }

                else -> false
            }
        }
    }
    private fun loadFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navigationContainer, fragment)
            .commit()
    }
}
