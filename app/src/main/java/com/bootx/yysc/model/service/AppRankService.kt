package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.AppRankEntityListResponse
import com.bootx.yysc.model.entity.AppRankSearchEntityListResponse
import com.bootx.yysc.model.entity.AppVersionListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface AppRankService {

    @POST("/api/appRank")
    suspend fun appRank(
        @Header("token") token: String,
    ): AppRankEntityListResponse

    @POST("/api/appRank/search")
    @FormUrlEncoded
    suspend fun search(
        @Header("token") token: String,
        @Field("type") type: Int,
        @Field("type1") type1: Int,
    ): AppRankSearchEntityListResponse

    companion object {
        fun instance(): AppRankService {
            return HiRetrofit.create(AppRankService::class.java)
        }
    }
}