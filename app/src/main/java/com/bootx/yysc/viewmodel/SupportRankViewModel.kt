package com.bootx.yysc.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.model.service.RankEntity
import com.bootx.yysc.model.service.RewardService
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.util.SharedPreferencesUtils

class SupportRankViewModel:ViewModel() {
    private val rewardService = RewardService.instance()

    var list by mutableStateOf(listOf<RankEntity>())

    var pageNumber by mutableIntStateOf(1)
        private set


    var hasMore by mutableStateOf(true)

    var loading by mutableStateOf(false)
    var pageSize by mutableStateOf(20)
        private set




    suspend fun rank(context: Context,type: Int) {
        try {
            val res = rewardService.rank(SharedPreferencesUtils(context).get("token"),type)
            if (res.code == 0) {
                val tmpList = mutableListOf<RankEntity>()
                if (pageNumber != 1) {
                    tmpList.addAll(list)
                }
                tmpList.addAll(res.data)
                list = tmpList
                if (res.data.size == pageSize) {
                    hasMore = true
                    this.pageNumber += 1
                } else {
                    hasMore = false
                }
            }
        }catch (e: Throwable){
            CommonUtils.toast(context,"${e.message}")
        }
    }
}