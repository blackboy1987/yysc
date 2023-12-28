package com.bootx.yysc.model.entity

data class AppRankEntity(
    var id: Int,
    var title: String="",
    var children: List<AppRankEntity> = listOf(),
)
data class AppRankSearchEntity(
    var id: Int,
    var logo: String="",
    var name: String="",
    var score: String="",
    var versionName: String="",
    var memo: String="",
)
data class AppRankEntityListResponse(val data: List<AppRankEntity>) : BaseResponse()
data class AppRankSearchEntityListResponse(val data: List<AppRankSearchEntity>) : BaseResponse()

