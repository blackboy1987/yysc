package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.CategoryListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.POST


interface TouGaoService {

    @POST("/api/member/touGao/category")
    suspend fun category(): CategoryListResponse

    companion object {
        fun instance(): TouGaoService {
            return HiRetrofit.create(TouGaoService::class.java)
        }
    }
}