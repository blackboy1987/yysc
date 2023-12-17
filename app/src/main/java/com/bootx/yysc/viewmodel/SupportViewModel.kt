package com.bootx.yysc.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.SoftDetailEntity
import com.bootx.yysc.model.service.LoadAdEntity
import com.bootx.yysc.model.service.RewardService
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.util.SharedPreferencesUtils

class SupportViewModel:ViewModel() {
    private val rewardService = RewardService.instance()

    var loadAdEntity by mutableStateOf<LoadAdEntity>(LoadAdEntity())


    suspend fun reward(context: Context) {
        try {
            val res = rewardService.ad(SharedPreferencesUtils(context).get("token"))
            if (res.code == 0) {

            }
        }catch (e: Throwable){
            CommonUtils.toast(context,"${e.message}")
        }
    }

    suspend fun loadAd(context: Context) {
        try {
            val res = rewardService.loadAd(SharedPreferencesUtils(context).get("token"))
            if (res.code == 0) {
                loadAdEntity = res.data
            }
        }catch (e: Throwable){
            CommonUtils.toast(context,"${e.message}")
        }
    }
}