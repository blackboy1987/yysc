package com.bootx.yysc.model.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MemberEntity(
    var avatar: String="",
    var username: String="",
    var createdDate: String="",
    var point: Int=0,
    var rankName: String="",
    var concernCount: Int=0,
    var fanCount: Int=0,
)