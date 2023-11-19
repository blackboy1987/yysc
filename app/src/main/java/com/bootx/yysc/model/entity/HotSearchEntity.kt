package com.bootx.yysc.model.entity

data class HotSearchEntity(
    val name: String
)

data class HotSearchListResponse(val data: List<HotSearchEntity>?) : BaseResponse()