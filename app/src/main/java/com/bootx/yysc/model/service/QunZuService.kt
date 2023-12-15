package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.QunZuEntityListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Header
import retrofit2.http.POST


interface QunZuService {

    @POST("/api/qunZu")
    suspend fun list(
        @Header("token") token: String,
    ): QunZuEntityListResponse

    companion object {
        fun instance(): QunZuService {
            return HiRetrofit.create(QunZuService::class.java)
        }
    }
}