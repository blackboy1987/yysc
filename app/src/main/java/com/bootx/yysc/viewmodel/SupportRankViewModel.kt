package com.bootx.yysc.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.service.RankEntity
import com.bootx.yysc.model.service.RewardService
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.util.SharedPreferencesUtils

class SupportRankViewModel:ViewModel() {
    private val rewardService = RewardService.instance()

    var rankEntityList = mutableListOf<RankEntity>()
    suspend fun rank(context: Context,type: Int) {
        try {
            val res = rewardService.rank(SharedPreferencesUtils(context).get("token"),type)
            if (res.code == 0) {
                val tmpList = mutableListOf<RankEntity>()
                tmpList.addAll(res.data)
                rankEntityList = tmpList
            }
        }catch (e: Throwable){
            CommonUtils.toast(context,"${e.message}")
        }
    }
}