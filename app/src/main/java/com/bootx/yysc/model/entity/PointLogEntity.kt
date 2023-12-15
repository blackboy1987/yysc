package com.bootx.yysc.model.entity

data class PointLogEntity(
    val id: Int,
    val memo: String,
    val point: Int,
    val seconds: String,
)

data class PointLogListResponse(val data: List<PointLogEntity>?) : BaseResponse()
