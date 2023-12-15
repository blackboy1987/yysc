package com.bootx.yysc.viewmodel

import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.service.SoftIconLogService

class AppDetailViewModel:ViewModel() {

    private var softIconLogService = SoftIconLogService.instance()

    suspend fun reward(token: String,softId: String,point: Int,memo: String) {
        val res = softIconLogService.reward(token,softId,point,memo)
    }
}