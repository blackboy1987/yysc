package com.bootx.yysc.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.AppRankEntity
import com.bootx.yysc.model.entity.AppRankSearchEntity
import com.bootx.yysc.model.service.AppRankService
import com.bootx.yysc.util.SharedPreferencesUtils

class AppRankViewModel:ViewModel() {

    private var appRankService = AppRankService.instance()

    var list by mutableStateOf(listOf<AppRankEntity>())

    var list1 by mutableStateOf(listOf<AppRankSearchEntity>())

    var pageNumber by mutableIntStateOf(1)
        private set


    var hasMore by mutableStateOf(true)

    var loading by mutableStateOf(false)
    var pageSize by mutableStateOf(20)
        private set

    suspend fun appRank(context: Context) {
        val res = appRankService.appRank(SharedPreferencesUtils(context).get("token"))
        if(res.code==0){
            list = res.data
        }
    }

    suspend fun search(context: Context,type: Int ,type1: Int) {
        try {
            val res = appRankService.search(SharedPreferencesUtils(context).get("token"),type,type1)
            if (res.code == 0) {
                val tmpList = mutableListOf<AppRankSearchEntity>()
                if (pageNumber != 1) {
                    tmpList.addAll(list1)
                }
                tmpList.addAll(res.data)
                list1 = tmpList
                if (res.data.size == pageSize) {
                    hasMore = true
                    this.pageNumber += 1
                } else {
                    hasMore = false
                }
            }
        }catch (e: Throwable){
            Log.e("search", "search: ${e.toString()}", )
        }
    }
}