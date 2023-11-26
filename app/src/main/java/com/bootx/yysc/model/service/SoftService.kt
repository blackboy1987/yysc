package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.SoftDetailResponse
import com.bootx.yysc.model.entity.SoftListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query


interface SoftService {

    @POST("/api/soft/list")
    @FormUrlEncoded
    suspend fun list(
        @Field("categoryId") categoryId: Int,
        @Field("pageNumber") pageNumber: Int,
        @Field("pageSize") pageSize: Int
    ): SoftListResponse

    @POST("/api/soft/orderBy")
    @FormUrlEncoded
    suspend fun orderBy(
        @Field("pageNumber") pageNumber: Int,
        @Field("pageSize") pageSize: Int,
        @Field("orderBy") orderBy: String,
        @Field("categoryId") categoryId: Int = 0,
    ): SoftListResponse


    @POST("/api/soft/detail")
    @FormUrlEncoded
    suspend fun detail(
        @Field("id") id: String,
    ): SoftDetailResponse

    companion object {
        fun instance(): SoftService {
            return HiRetrofit.create(SoftService::class.java)
        }
    }
}