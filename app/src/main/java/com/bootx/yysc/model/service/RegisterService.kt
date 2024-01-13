package com.bootx.yysc.model.service

import android.provider.ContactsContract.CommonDataKinds.Email
import com.bootx.yysc.model.entity.LoginEntityResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface RegisterService {

    @POST("/api/member/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String,
        @Field("spreadMemberUsername") spreadMemberUsername: String
    ): LoginEntityResponse

    companion object {
        fun instance(): RegisterService {
            return HiRetrofit.create(RegisterService::class.java)
        }
    }
}