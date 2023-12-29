package com.bootx.yysc.ui.components.ad

import android.app.Activity
import android.content.Context
import com.bootx.yysc.model.entity.AdConfig
import com.bootx.yysc.util.SharedPreferencesUtils
import com.google.gson.Gson
import com.youxiao.ssp.ad.bean.NextAdInfo
import com.youxiao.ssp.ad.bean.SSPAd
import com.youxiao.ssp.ad.core.AdClient
import com.youxiao.ssp.ad.listener.RewardVideoAdAdapter

/**
*激励视频广告：1028
*/
fun requestRewardAd(context: Context, onClose:(type:String)->Unit) {
    val adClient = AdClient(context as Activity)
    val gson = Gson()
    val get = SharedPreferencesUtils(context).get("adConfig")
    val adConfig = gson.fromJson(get, AdConfig::class.java)
    adClient.requestRewardAd(adConfig.rewardVideoAdId, object : RewardVideoAdAdapter() {
        override fun loadRewardAdSuc(p0: SSPAd?) {
            super.loadRewardAdSuc(p0)
            onClose("loadRewardAdSuc")
        }

        override fun loadRewardAdFail(p0: String?) {
            super.loadRewardAdFail(p0)
            onClose("loadRewardAdFail")
        }

        override fun loadRewardVideoSuc() {
            super.loadRewardVideoSuc()
            onClose("loadRewardVideoSuc")
        }

        override fun loadRewardVideoFail(p0: Int, p1: Int) {
            super.loadRewardVideoFail(p0, p1)
            onClose("loadRewardVideoFail")
        }

        override fun startPlayRewardVideo() {
            super.startPlayRewardVideo()
            onClose("startPlayRewardVideo")
        }

        override fun playRewardVideoAQuarter() {
            super.playRewardVideoAQuarter()
            onClose("playRewardVideoAQuarter")
        }

        override fun playRewardVideoHalf() {
            super.playRewardVideoHalf()
            onClose("playRewardVideoHalf")
        }

        override fun playRewardVideoThreeQuarters() {
            super.playRewardVideoThreeQuarters()
        }

        override fun playRewardVideoCompleted(p0: SSPAd?) {
            super.playRewardVideoCompleted(p0)
            onClose("playRewardVideoCompleted")
        }

        override fun onReward(p0: SSPAd?, p1: Boolean, p2: MutableMap<String, Any>?) {
            super.onReward(p0, p1, p2)
            onClose("onReward")
        }

        override fun getExtraInfo(p0: SSPAd?): Any {
            return super.getExtraInfo(p0)
            onClose("getExtraInfo")
        }

        override fun rewardVideoClick() {
            super.rewardVideoClick()
            onClose("rewardVideoClick")
        }

        override fun rewardVideoButtonClick() {
            super.rewardVideoButtonClick()
            onClose("rewardVideoButtonClick")
        }

        override fun rewardVideoClosed() {
            super.rewardVideoClosed()
            onClose("rewardVideoClosed")
        }
    })
}