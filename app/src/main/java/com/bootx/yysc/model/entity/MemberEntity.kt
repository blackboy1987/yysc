package com.bootx.yysc.model.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MemberEntity(
    var avatar: String="",
    var username: String="",
    var createdDate: String="",
    var point: String="",
    var rankName: String="",
    var concernCount: String="",
    var fanCount: String="",
)