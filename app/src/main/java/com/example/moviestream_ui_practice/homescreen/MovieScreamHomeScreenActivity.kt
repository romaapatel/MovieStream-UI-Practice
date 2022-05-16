package com.example.moviestream_ui_practice.homescreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviestream_ui_practice.R
import kotlinx.android.synthetic.main.activity_movie_scream_home_screen.bottomNav


class MovieScreamHomeScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_scream_home_screen)
        supportFragmentManager.beginTransaction().add(R.id.framePager, HomeFragment()).commit()
        supportActionBar?.hide()
        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navHome -> {
                    supportFragmentManager.beginTransaction().replace(R.id.framePager, HomeFragment()).commit()
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}