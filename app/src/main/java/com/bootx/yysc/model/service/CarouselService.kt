package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.CarouselListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Header
import retrofit2.http.POST


interface CarouselService {

    @POST("/api/carousel")
    suspend fun list(@Header("token") token: String): CarouselListResponse


    companion object {
        fun instance(): CarouselService {
            return HiRetrofit.create(CarouselService::class.java)
        }
    }
}