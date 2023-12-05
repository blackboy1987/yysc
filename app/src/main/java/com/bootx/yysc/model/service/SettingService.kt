package com.bootx.yysc.model.service

import com.bootx.yysc.model.entity.BaseResponse
import com.bootx.yysc.util.HiRetrofit
import com.squareup.moshi.JsonClass
import retrofit2.http.Header
import retrofit2.http.POST


@JsonClass(generateAdapter = true)
data class SettingEntity(
    var userMenus: List<UserMenu> = listOf(),
)

data class UserMenu(
    var icon: String,
    var title: String,
)
data class SettingEntityResponse(val data: SettingEntity) : BaseResponse()

interface SettingService {

    @POST("/api/setting")
    suspend fun setting(
        @Header("token") token: String
    ): SettingEntityResponse

    companion object {
        fun instance(): SettingService {
            return HiRetrofit.create(SettingService::class.java)
        }
    }
}