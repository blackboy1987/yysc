package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.NotifyEntityListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface NotifyService {

    @POST("/api/member/message/list")
    @FormUrlEncoded
    suspend fun list(
        @Header("token") token: String,
        @Field("type") type: String,
        @Field("pageNumber") pageNumber: Int,
    ): NotifyEntityListResponse

    companion object {
        fun instance(): NotifyService {
            return HiRetrofit.create(NotifyService::class.java)
        }
    }
}