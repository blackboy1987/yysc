package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.BaseResponse
import com.bootx.yysc.model.entity.CommonResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface RewardService {

    @POST("/api/member/reward/ad")
    suspend fun ad(
        @Header("token") token: String,
    ): CommonResponse

    @POST("/api/member/reward/loadAd")
    suspend fun loadAd(
        @Header("token") token: String,
    ): LoadAdEntityResponse



    @POST("/api/member/reward/rank")
    @FormUrlEncoded
    suspend fun rank(
        @Header("token") token: String,
        @Field("type") type: Int,
    ): RankEntityListResponse

    companion object {
        fun instance(): RewardService {
            return HiRetrofit.create(RewardService::class.java)
        }
    }
}

data class LoadAdEntity(
    val point: Int = 0,
    val times: Int = 0,
)

data class RankEntity(
    val username: String = "",
    val avatar: String = "",
    val rankName: String = "",
    val times: Int = 0,
)

data class LoadAdEntityResponse(val data: LoadAdEntity) : BaseResponse()
data class RankEntityListResponse(val data: List<RankEntity>) : BaseResponse()