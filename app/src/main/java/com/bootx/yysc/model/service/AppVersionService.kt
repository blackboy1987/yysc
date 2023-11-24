package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.AppVersionListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface AppVersionService {

    @POST("/api/appVersion/check")
    @FormUrlEncoded
    suspend fun check(@Field("versionCode") versionCode: String): AppVersionListResponse
    companion object {
        fun instance(): AppVersionService {
            return HiRetrofit.create(AppVersionService::class.java)
        }
    }
}