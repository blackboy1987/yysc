package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.CategoryDetailResponse
import com.bootx.yysc.model.entity.CategoryListResponse
import com.bootx.yysc.model.entity.SoftDetailResponse
import com.bootx.yysc.model.entity.SoftListResponse
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query


interface CategoryService {

    @POST("/api/category/list")
    @FormUrlEncoded
    suspend fun list(
        @Field("pageNumber") pageNumber: Int,
        @Field("pageSize") pageSize: Int
    ): CategoryListResponse


    @POST("/api/category/detail")
    @FormUrlEncoded
    suspend fun detail(
        @Field("id") id: Int,
    ): CategoryDetailResponse

    companion object {
        fun instance(): CategoryService {
            return HiRetrofit.create(CategoryService::class.java)
        }
    }
}