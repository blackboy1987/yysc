package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.HomeCenterBarListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.POST


interface HomeService {

    @POST("/api/homeCenterBar")
    suspend fun homeCenterBar(): HomeCenterBarListResponse

    companion object {
        fun instance(): HomeService {
            return HiRetrofit.create(HomeService::class.java)
        }
    }
}