package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.BaseResponse
import com.bootx.yysc.model.entity.CommonResponse
import com.bootx.yysc.repository.entity.UserEntity
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

data class UserEntityResponse(val data: UserEntity) : BaseResponse()

interface UserService {

    @POST("/api/member/currentUser")
    suspend fun currentUser(
        @Header("token") token: String,
    ): UserEntityResponse
    @POST("/api/member/update")
    @FormUrlEncoded
    suspend fun update(@Header("token") token: String, @Field("username") username: String): CommonResponse

    companion object {
        fun instance(): UserService {
            return HiRetrofit.create(UserService::class.java)
        }
    }
}