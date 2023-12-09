package com.bootx.yysc.model.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInEntity(
    var days: Int=0,
    var isSign: Boolean=false,
    var rank: Int=0,
    var list: List<SignRank> = listOf()
)

@JsonClass(generateAdapter = true)
data class SignRank(
    var username: String="",
    val continuousSignInDays: Int=0,
    var avatar: String="",
    var signDate: String="",
)

data class SignInEntityListResponse(val data: List<SignInEntity>?) : BaseResponse()
data class SignInEntityResponse(val data: SignInEntity) : BaseResponse()