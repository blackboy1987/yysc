package com.bootx.yysc.model.entity

import java.io.File

sealed class DownloadState {
    data class InProgress(val progress: Int) : DownloadState()
    data class Success(val file: File) : DownloadState()
    data class Error(val errMsg: String) : DownloadState()
}