package com.bootx.yysc.model.entity

data class ActivityEntity(
    var title: String="",
    var image: String="",
    var url: String="",
)

data class ActivityEntityListResponse(val data: List<ActivityEntity>?) : BaseResponse()

