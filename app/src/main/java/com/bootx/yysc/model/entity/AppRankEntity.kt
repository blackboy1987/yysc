package com.bootx.yysc.model.entity

data class AppRankEntity(
    var id: Int,
    var title: String="",
    var children: List<AppRankEntity> = listOf(),
)

data class AppRankEntityListResponse(val data: List<AppRankEntity>) : BaseResponse()

