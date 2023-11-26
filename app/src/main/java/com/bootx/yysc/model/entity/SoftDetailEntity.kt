package com.bootx.yysc.model.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SoftDetailEntity(
    var id: Int=0,
    var name: String="",
    var fullName: String="",
    val size: String="",
    var memo: String="",
    var logo: String="",
    var updateDate: String="",
    val score: String="",
    val downloadUrl: String="",
    val downloads: String="",
    var images: List<String> = listOf(),
    var versionName: String="",
    var introduce: String=""
)

data class SoftDetailResponse(val data: SoftDetailEntity?) : BaseResponse()