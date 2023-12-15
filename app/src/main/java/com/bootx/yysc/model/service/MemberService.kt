package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.BaseResponse
import com.bootx.yysc.model.entity.MemberEntity
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

data class MemberEntityResponse(val data: MemberEntity) : BaseResponse()

interface MemberService {

    @POST("/api/member/load")
    @FormUrlEncoded
    suspend fun load(
        @Header("token") token: String,
        @Field("id") id: String,
    ): MemberEntityResponse

    companion object {
        fun instance(): MemberService {
            return HiRetrofit.create(MemberService::class.java)
        }
    }
}