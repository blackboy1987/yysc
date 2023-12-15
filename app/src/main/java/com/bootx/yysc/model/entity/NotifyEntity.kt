package com.bootx.yysc.model.entity

data class NotifyEntity(
    val title: String,
    val memo: String,
)

data class NotifyEntityListResponse(val data: List<NotifyEntity>) : BaseResponse()
