package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.CategoryDetailResponse
import com.bootx.yysc.model.entity.CategoryListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface CategoryService {

    @POST("/api/category/list")
    @FormUrlEncoded
    suspend fun list(
        @Header("token") token: String,
        @Field("pageNumber") pageNumber: Int,
        @Field("pageSize") pageSize: Int
    ): CategoryListResponse


    @POST("/api/category/detail")
    @FormUrlEncoded
    suspend fun detail(
        @Header("token") token: String,
        @Field("id") id: Int,
    ): CategoryDetailResponse

    companion object {
        fun instance(): CategoryService {
            return HiRetrofit.create(CategoryService::class.java)
        }
    }
}