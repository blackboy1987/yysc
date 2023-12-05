package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.ComplaintsEntityResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface ComplaintsService {

    @POST("/api/member/complaint/save")
    @FormUrlEncoded
    suspend fun save(
        @Header("token") token: String,
        @Field("type") type: Int,
        @Field("reason") reason: String,
        @Field("images") images: String,
        @Field("softId") softId: String
    ): ComplaintsEntityResponse

    companion object {
        fun instance(): ComplaintsService {
            return HiRetrofit.create(ComplaintsService::class.java)
        }
    }
}