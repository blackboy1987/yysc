package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.ActivityEntityListResponse
import com.bootx.yysc.model.entity.HomeCenterBarListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Header
import retrofit2.http.POST


interface HomeService {

    @POST("/api/homeCenterBar")
    suspend fun homeCenterBar(@Header("token") token: String): HomeCenterBarListResponse

    @POST("/api/activity")
    suspend fun activity(@Header("token") token: String): ActivityEntityListResponse

    companion object {
        fun instance(): HomeService {
            return HiRetrofit.create(HomeService::class.java)
        }
    }
}