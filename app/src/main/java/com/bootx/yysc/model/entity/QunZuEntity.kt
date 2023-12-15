package com.bootx.yysc.model.entity

data class QunZuEntity(
    val title1: String,
    val title2: String,
    val image: String,
    val url: String,
)

data class QunZuEntityListResponse(val data: List<QunZuEntity>) : BaseResponse()