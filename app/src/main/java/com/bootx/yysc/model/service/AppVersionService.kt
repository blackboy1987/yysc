package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.AppVersionListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


interface AppVersionService {

    @POST("/api/appVersion/check")
    @FormUrlEncoded
    suspend fun check(
        @Header("token") token: String,
        @Field("versionCode") versionCode: String,
        @Field("versionName") versionName: String
    ): AppVersionListResponse

    companion object {
        fun instance(): AppVersionService {
            return HiRetrofit.create(AppVersionService::class.java)
        }
    }
}