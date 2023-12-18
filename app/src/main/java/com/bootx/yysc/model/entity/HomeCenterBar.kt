package com.bootx.yysc.model.entity

data class HomeCenterBar(
    var name: String="",
    var image: String="",
)

data class HomeCenterBarListResponse(val data: List<HomeCenterBar>?) : BaseResponse()

