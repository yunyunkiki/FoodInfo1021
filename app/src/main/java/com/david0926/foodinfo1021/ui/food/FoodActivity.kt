package com.david0926.foodinfo1021.ui.food

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.david0926.foodinfo1021.R
import com.david0926.foodinfo1021.data.model.Food
import com.david0926.foodinfo1021.databinding.ActivityFoodBinding
import com.david0926.foodinfo1021.ui.common.BaseActivity
import com.david0926.foodinfo1021.ui.common.ViewPagerAdapter

class FoodActivity : BaseActivity<ActivityFoodBinding>(R.layout.activity_food) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val food = intent.getSerializableExtra("food") as Food
        binding.food = food

        val fragments = listOf(
            FoodImageFragment.newInstance(food.imgurl1),
            FoodImageFragment.newInstance(food.imgurl2)
        )

        binding.vpFood.adapter = ViewPagerAdapter(this, fragments)
        binding.vpFood.offscreenPageLimit = fragments.size - 1

        binding.toolbarFood.setNavigationOnClickListener { finish() }
        binding.btFoodShare.setOnClickListener { shareFood(food) }
    }

    private fun shareFood(food: Food) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "[${food.prdlstNm}](${food.imgurl1})\n${food.rawmtrl}")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, getString(R.string.food_share))
        startActivity(shareIntent)
    }
}