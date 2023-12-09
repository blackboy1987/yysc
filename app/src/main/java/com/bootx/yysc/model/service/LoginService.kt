package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.BaseResponse
import com.bootx.yysc.model.entity.LoginEntity
import com.bootx.yysc.model.entity.LoginEntityResponse
import com.bootx.yysc.repository.entity.UserEntity
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface LoginService {

    @POST("/api/member/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginEntityResponse

    companion object {
        fun instance(): LoginService {
            return HiRetrofit.create(LoginService::class.java)
        }
    }
}