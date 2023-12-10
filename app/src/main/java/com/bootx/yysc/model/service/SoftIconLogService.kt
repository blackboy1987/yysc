package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.BaseResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

data class SoftIconLogRewardResponse(val data: String) : BaseResponse()

interface SoftIconLogService {

    @POST("/api/member/softIconLog/reward")
    @FormUrlEncoded
    suspend fun reward(
        @Header("token") token: String,
        @Field("softId") softId: String,
        @Field("point") point: Int,
        @Field("memo") memo: String,
    ): SoftIconLogRewardResponse

    companion object {
        fun instance(): SoftIconLogService {
            return HiRetrofit.create(SoftIconLogService::class.java)
        }
    }
}