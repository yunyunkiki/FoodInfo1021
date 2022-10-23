package com.david0926.foodinfo1021.data.repository


import com.david0926.foodinfo1021.data.model.FoodRequest
import com.david0926.foodinfo1021.data.model.FoodResponse
import com.david0926.foodinfo1021.data.remote.FoodService
import retrofit2.Response
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodService: FoodService
) : FoodRepository {

    override suspend fun getFoods(foodRequest: FoodRequest): Response<FoodResponse> {
        return foodService.getFoods(
            ServiceKey = foodRequest.ServiceKey,
            prdlstReportNo = foodRequest.prdlstReportNo,
            prdlstNm = foodRequest.prdlstNm,
            returnType = foodRequest.returnType,
            pageNo = foodRequest.pageNo,
            numOfRows = foodRequest.numOfRows
        )
    }
}