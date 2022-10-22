package com.david0926.foodinfo1021.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.david0926.foodinfo1021.R
import com.david0926.foodinfo1021.ui.onboard.OnboardActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = getSharedPreferences("preference", Context.MODE_PRIVATE)
        if (sharedPref.getBoolean("IS_FIRST_LAUNCH", true)) {
            startActivity(Intent(this, OnboardActivity::class.java))
        }
    }
}