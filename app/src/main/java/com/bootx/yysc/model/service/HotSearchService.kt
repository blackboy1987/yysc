package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.HotSearchListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Header
import retrofit2.http.POST


interface HotSearchService {

    @POST("/api/hotSearch")
    suspend fun fetchList(@Header("token") token: String): HotSearchListResponse

    companion object {
        fun instance(): HotSearchService {
            return HiRetrofit.create(HotSearchService::class.java)
        }
    }
}