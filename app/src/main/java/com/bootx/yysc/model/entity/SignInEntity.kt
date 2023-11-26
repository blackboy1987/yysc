package com.bootx.yysc.model.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInEntity(
    var name: String="",
    val time: String="",
    var days: Int=0,
    var avatar: String="",
)

data class SignInEntityResponse(val data: List<SignInEntity>?) : BaseResponse()