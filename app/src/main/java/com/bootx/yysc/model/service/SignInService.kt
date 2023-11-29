package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.SignInEntity
import com.bootx.yysc.model.entity.SoftListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Header
import retrofit2.http.POST


interface SignInService {

    @POST("/api/member/signIn/list")
    suspend fun list(@Header("token") token: String): SoftListResponse

    @POST("/api/member/signIn/isSign")
    suspend fun isSign(@Header("token") token: String): Boolean

    @POST("/api/member/signIn")
    suspend fun signIn(@Header("token") token: String): SignInEntity

    companion object {
        fun instance(): SignInService {
            return HiRetrofit.create(SignInService::class.java)
        }
    }
}