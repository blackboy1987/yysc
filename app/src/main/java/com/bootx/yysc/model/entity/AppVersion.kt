package com.bootx.yysc.model.entity

data class AppVersion(
    var downloadUrl: String?="",
    var versionCode: String?="",
    var memo: String?="",
)

data class AppVersionResponse(val data: AppVersion?) : BaseResponse()
data class AppVersionListResponse(val data: List<AppVersion>?) : BaseResponse()

