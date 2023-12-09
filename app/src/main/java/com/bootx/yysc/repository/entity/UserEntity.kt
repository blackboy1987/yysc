package com.bootx.yysc.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user"
)
data class UserEntity(
    @PrimaryKey
    var id: Int,
    var avatar: String,
    var username: String,
    var point: Int = 0,
    var nextPoint: Int = 1000,
)