package com.bootx.yysc.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user"
)
data class UserEntity(
    @PrimaryKey
    var id: Int=0,
    // 头像
    var avatar: String="",
    // 用户名
    var username: String="",
    // 当前积分
    var point: Int = 0,
    // 下个等级的积分
    var nextPoint: Int = 1000,
    // 关注的人数
    var concernCount: Int = 0,
    // 粉丝人数
    var fanCount: Int = 0,
    // 付费购买数量
    var payCount: Int = 0,
)