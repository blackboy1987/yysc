package com.bootx.yysc.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.NotifyEntity
import com.bootx.yysc.model.service.NotifyService
import com.bootx.yysc.util.SharedPreferencesUtils

class NotifyViewModel:ViewModel() {

    private var notifyService = NotifyService.instance()

    var list by mutableStateOf(listOf<NotifyEntity>())

    var pageNumber by mutableStateOf(1)
        private set

    suspend fun list(context: Context, type: String) {
        val res = notifyService.list(SharedPreferencesUtils(context).get("token"),type,pageNumber)
        if (res.code == 0) {
            val tmpList = mutableListOf<NotifyEntity>()
            if (pageNumber != 1) {
                tmpList.addAll(list)
            }
            tmpList.addAll(res.data)
            list = tmpList
            pageNumber += 1
        }
    }
}