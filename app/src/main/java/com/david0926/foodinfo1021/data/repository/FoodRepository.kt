package com.david0926.foodinfo1021.data.repository

import com.david0926.foodinfo1021.data.model.FoodRequest
import com.david0926.foodinfo1021.data.model.FoodResponse
import retrofit2.Response

interface FoodRepository {
    suspend fun getFoods(foodRequest: FoodRequest): Response<FoodResponse>
}