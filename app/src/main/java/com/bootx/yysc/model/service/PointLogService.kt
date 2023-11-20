package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.PointLogListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface PointLogService {

    @POST("/api/member/pointLog/list")
    @FormUrlEncoded
    suspend fun list(
        @Field("pageNumber") pageNumber: Int,
        @Field("pageSize") pageSize: Int
    ): PointLogListResponse

    companion object {
        fun instance(): PointLogService {
            return HiRetrofit.create(PointLogService::class.java)
        }
    }
}