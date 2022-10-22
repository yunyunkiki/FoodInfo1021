package com.david0926.foodinfo1021.ui.onboard

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.david0926.foodinfo1021.R
import com.david0926.foodinfo1021.data.model.Onboard
import com.david0926.foodinfo1021.databinding.ActivityOnboardBinding
import com.david0926.foodinfo1021.ui.common.BaseActivity
import com.david0926.foodinfo1021.ui.common.ViewPagerAdapter

class OnboardActivity : BaseActivity<ActivityOnboardBinding>(R.layout.activity_onboard) {

    private lateinit var sharedPref: SharedPreferences
    private val viewModel by viewModels<OnboardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        sharedPref = getSharedPreferences("preference", Context.MODE_PRIVATE)

        val fragments = initOnboardFragments()

        binding.maxPage = fragments.size
        binding.vpOnboard.adapter = ViewPagerAdapter(this, fragments)
        binding.vpOnboard.offscreenPageLimit = fragments.size - 1

        binding.vpOnboard.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setCurrentPage(position)
            }
        })

        binding.btOnboardSkip.setOnClickListener { finish() }
        binding.btOnboardFinish.setOnClickListener { finish() }

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val page = viewModel.currentPage.value!!
                if (page == 0) finish()
                else binding.vpOnboard.currentItem = page - 1
            }
        })
    }

    private fun initOnboardFragments(): List<Fragment> {
        return listOf(
            OnboardFragment.newInstance(
                Onboard(
                    getString(R.string.lottie_onboard_1),
                    getString(R.string.onboard_title_1),
                    getString(R.string.onboard_description_1)
                )
            ),
            OnboardFragment.newInstance(
                Onboard(
                    getString(R.string.lottie_onboard_2),
                    getString(R.string.onboard_title_2),
                    getString(R.string.onboard_description_2)
                )
            ),
            OnboardFragment.newInstance(
                Onboard(
                    getString(R.string.lottie_onboard_3),
                    getString(R.string.onboard_title_3),
                    getString(R.string.onboard_description_3)
                )
            ),
        )
    }

    override fun finish() {
        with(sharedPref.edit()){
            putBoolean("IS_FIRST_LAUNCH", false)
            apply()
        }
        super.finish()
    }
}