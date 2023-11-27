package com.bootx.yysc.model.entity

data class TouGaoEntity(
    var categoryId0: Int=0,
    var quDaoId: Int=0,
    var categoryId1: Int=0,
    var images: List<String> = listOf<String>(),
    var title: String="",
    var memo: String="",
    var introduce: String="",
    var updatedContent: String="",
    var adType: Int=0,
    var paidType: Int=0,
    var operationType: Int=0,
    var featuresType: Int=0,
)
