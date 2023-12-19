package com.bootx.yysc.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.HotSearchEntity
import com.bootx.yysc.model.service.HotSearchService
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.util.SharedPreferencesUtils

class HotSearchViewModel:ViewModel() {
    private val hotSearchService = HotSearchService.instance()

    var list by mutableStateOf(listOf<HotSearchEntity>())


    suspend fun fetchList(context: Context){
        try {
            val res = hotSearchService.fetchList(SharedPreferencesUtils(context).get("token"))
            if (res.code == 0 && res.data != null) {
                val tmpList = mutableListOf<HotSearchEntity>()
                tmpList.addAll(res.data)
                list = tmpList
            }
        }catch (e: Throwable){
            CommonUtils.toast(context,"${e.message}")
        }
    }
}