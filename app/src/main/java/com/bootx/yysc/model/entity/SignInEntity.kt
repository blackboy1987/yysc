package com.bootx.yysc.model.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInEntity(
    var days: Int=0,
    var isSign: Boolean=false,
    var rank: Int=0,
)

@JsonClass(generateAdapter = true)
data class SignRank(
    var username: String="",
    val continuousSignInDays: Int=0,
    var avatar: String="",
    var signDate: String="",
)

data class SignRankListResponse(val data: List<SignRank>) : BaseResponse()
data class SignInEntityResponse(val data: SignInEntity) : BaseResponse()