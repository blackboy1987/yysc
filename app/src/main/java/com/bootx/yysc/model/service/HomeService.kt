package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.BaseResponse
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.util.HiRetrofit
import retrofit2.http.Header
import retrofit2.http.POST


interface HomeService {

    @POST("/api/home")
    suspend fun load(@Header("token") token: String): HomeDataResponse

    companion object {
        fun instance(): HomeService {
            return HiRetrofit.create(HomeService::class.java)
        }
    }
}



data class HomeDataResponse(val data: HomeData) : BaseResponse()

data class HomeData(
    val todayDownloadList: List<SoftEntity> = listOf(),
    val randomSeeList: List<SoftEntity> = listOf(),
    val activities: List<ActivityData> = listOf(),
    val carousels: List<CarouselData> = listOf(),
    val todayCommentList: List<SoftEntity> = listOf(),
    val centerBars: List<CenterBarData> = listOf(),
)

data class ActivityData(
    val image: String,
    val title: String,
    val url: String,
)

data class CarouselData(
    val id: Int = 0,
    val title2: String="",
    val title1: String="",
    val image: String,
    val logo: String,
    val downloadUrl: String,
)

data class CenterBarData(
    val name: String,
    val image: String,
    val isEnabled: Boolean,
)
