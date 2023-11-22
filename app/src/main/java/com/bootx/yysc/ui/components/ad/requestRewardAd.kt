package com.bootx.yysc.ui.components.ad

import android.app.Activity
import android.content.Context
import com.bootx.yysc.config.Config
import com.youxiao.ssp.ad.bean.SSPAd
import com.youxiao.ssp.ad.core.AdClient
import com.youxiao.ssp.ad.listener.RewardVideoAdAdapter

/**
*激励视频广告：1028
*/
fun requestRewardAd(context: Context, onClose:(type:Int)->Unit) {
    var status: Int = 0
    val adClient = AdClient(context as Activity)
    adClient.requestRewardAd(Config.REWARD_VIDEO_AD_ID, object : RewardVideoAdAdapter() {
        override fun onReward(type: Int) {
            super.onReward(type)
            status = 0
        }
        override fun playRewardVideoCompleted(type: SSPAd) {
            super.playRewardVideoCompleted(type)
            status = 1
        }
        override fun loadRewardVideoSuc() {
            status = 2
        }
        override fun loadRewardVideoFail(var1: Int,var2: Int) {
            status = -1
        }
        override fun startPlayRewardVideo() {
            status = 3
        }
        override fun playRewardVideoHalf() {
            status = 4
        }
        override fun rewardVideoClick() {
            status = 5
        }
        override fun rewardVideoButtonClick() {
            status = 6
        }
        override fun rewardVideoClosed() {
            onClose(status)
        }
    })
}