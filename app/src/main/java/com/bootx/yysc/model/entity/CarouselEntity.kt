package com.bootx.yysc.model.entity

data class CarouselEntity(
    val id: Int,
    val title1: String,
    val title2: String,
    val image: String,
    val logo: String,
    val downloadUrl: String
)

data class CarouselListResponse(val data: List<CarouselEntity>?) : BaseResponse()
