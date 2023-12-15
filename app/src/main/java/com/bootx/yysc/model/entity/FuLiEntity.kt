package com.bootx.yysc.model.entity

data class FuLiEntity(
    val title1: String,
    val title2: String,
    val image: String,
    val url: String,
)

data class FuLiEntityListResponse(val data: List<FuLiEntity>) : BaseResponse()