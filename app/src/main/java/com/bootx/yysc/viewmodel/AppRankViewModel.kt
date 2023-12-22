package com.bootx.yysc.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.AppRankEntity
import com.bootx.yysc.model.service.AppRankService
import com.bootx.yysc.util.SharedPreferencesUtils

class AppRankViewModel:ViewModel() {

    private var appRankService = AppRankService.instance()

    var list by mutableStateOf(listOf<AppRankEntity>())
    suspend fun appRank(context: Context) {
        val res = appRankService.appRank(SharedPreferencesUtils(context).get("token"))
        if(res.code==0){
            list = res.data
        }
    }
}