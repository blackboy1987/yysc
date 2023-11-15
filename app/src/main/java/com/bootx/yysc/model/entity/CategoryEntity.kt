package com.bootx.yysc.model.entity

data class CategoryEntity(
    val id: Int,
    val name: String
)

data class CategoryListResponse(val data: List<CategoryEntity>?) : BaseResponse()

data class CategoryDetailResponse(val data: CategoryEntity?) : BaseResponse()