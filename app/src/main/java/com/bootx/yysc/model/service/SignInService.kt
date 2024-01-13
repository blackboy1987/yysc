package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.SignInEntityResponse
import com.bootx.yysc.model.entity.SignRankListResponse
import com.bootx.yysc.model.entity.SoftListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface SignInService {

    @POST("/api/member/signIn/list")
    @FormUrlEncoded
    suspend fun list(
        @Header("token") token: String,
        @Field("pageNumber") pageNumber: Number,
        @Field("pageSize") pageSize: Number
    ): SignRankListResponse

    @POST("/api/member/signIn/isSign")
    suspend fun isSign(@Header("token") token: String): Boolean

    @POST("/api/member/signIn")
    suspend fun signIn(@Header("token") token: String): SignInEntityResponse

    companion object {
        fun instance(): SignInService {
            return HiRetrofit.create(SignInService::class.java)
        }
    }
}