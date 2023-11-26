package com.bootx.yysc.model.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DownloadEntity(
    var name: String,
    val size: String,
    val downloadUrl: String,
    var versionName: String
)

data class DownloadEntityResponse(val data: DownloadEntity?) : BaseResponse()