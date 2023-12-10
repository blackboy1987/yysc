package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.BaseResponse
import com.bootx.yysc.model.entity.CategoryListResponse
import com.bootx.yysc.util.HiRetrofit
import com.squareup.moshi.JsonClass
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST


interface TouGaoService {

    @POST("/api/member/touGao/category")
    suspend fun category(@Header("token") token: String): CategoryListResponse

    @POST("/api/member/touGao/save")
    @FormUrlEncoded
    suspend fun create(
        @Header("token") token: String,
        @Field("title") title: String,
        @Field("memo") memo: String,
        @Field("introduce") introduce: String,
        @Field("updatedContent") updatedContent: String,
        @Field("adType0") adType0: Int,
        @Field("adType1") adType1: Int,
        @Field("adType2") adType2: Int,
        @Field("adType3") adType3: Int,
        @Field("appLogo") appLogo: String,
        @Field("categoryId0") categoryId0: Int,
        @Field("categoryId1") categoryId1: Int,
        @Field("quDaoIndex") quDaoIndex: Int,
        @Field("urls") urls: String,
        @Field("versionCode") versionCode: String,
        @Field("versionName") versionName: String,
        @Field("minSdkVersion") minSdkVersion: Int,
        @Field("targetSdkVersion") targetSdkVersion: Int,
        @Field("size") size: Long
    )

    @POST("/api/member/touGao/list")
    @FormUrlEncoded
    suspend fun list(
        @Header("token") token: String,
        @Field("pageNumber") pageNumber: Int,
        @Field("pageSize") pageSize: Int,
        @Field("type") type: Int
    ): TouGaoEntityListResponse

    companion object {
        fun instance(): TouGaoService {
            return HiRetrofit.create(TouGaoService::class.java)
        }
    }
}


@JsonClass(generateAdapter = true)
data class TouGaoEntity(
    var id: Int = 0,
    var name: String = "",
    var logo: String = "",
    var versionName: String = "",
    var createdDate: String ="",
)

data class TouGaoEntityListResponse(val data: List<TouGaoEntity>?) : BaseResponse()