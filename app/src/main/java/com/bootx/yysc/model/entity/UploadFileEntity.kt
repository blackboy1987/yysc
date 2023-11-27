package com.bootx.yysc.model.entity

open class UploadFileEntity {
    var url: String = ""
}

data class UploadFileResponse(val data: UploadFileEntity) : BaseResponse()