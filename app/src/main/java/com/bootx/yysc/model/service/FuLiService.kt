package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.FuLiEntityListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Header
import retrofit2.http.POST


interface FuLiService {

    @POST("/api/fuLi")
    suspend fun list(
        @Header("token") token: String,
    ): FuLiEntityListResponse

    companion object {
        fun instance(): FuLiService {
            return HiRetrofit.create(FuLiService::class.java)
        }
    }
}