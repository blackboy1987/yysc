package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.BaseResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

data class FanEntity(
    var id: Int,
    var username: String,
    var point: Int,
    var avatar: String,
)

data class FanEntityListResponse(val data: List<FanEntity>) : BaseResponse()

interface FanService {

    @POST("/api/member/fan/list")
    @FormUrlEncoded
    suspend fun list(
        @Header("token") token: String,
        @Field("type") type: Int,
    ): FanEntityListResponse

    @POST("/api/member/fan/delete")
    @FormUrlEncoded
    suspend fun delete(
        @Header("token") token: String,
        @Field("fanId") fanId: Int,
        @Field("type") type: Int
    ): FanEntityListResponse

    companion object {
        fun instance(): FanService {
            return HiRetrofit.create(FanService::class.java)
        }
    }
}