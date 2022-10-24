package com.david0926.foodinfo1021.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.david0926.foodinfo1021.R
import com.david0926.foodinfo1021.databinding.ActivityMainBinding
import com.david0926.foodinfo1021.ui.common.BaseActivity
import com.david0926.foodinfo1021.ui.onboard.OnboardActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var sharedPref: SharedPreferences
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = getSharedPreferences("preference", Context.MODE_PRIVATE)
        if (sharedPref.getBoolean("IS_FIRST_LAUNCH", true)) {
            startActivity(Intent(this, OnboardActivity::class.java))
        }

        binding.viewModel = viewModel

        binding.rvMain.layoutManager = GridLayoutManager(this, 2)
        binding.rvMain.adapter = MainRecyclerAdapter()

        viewModel.getFoods()

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                binding.rvMain.run {
                    if (canScrollVertically(-1)) smoothScrollToPosition(0)
                    else finish()
                }
            }
        })
    }
}