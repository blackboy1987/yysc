package com.bootx.yysc.model.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserEntity(
    var id: Int,
    var name: String,
)