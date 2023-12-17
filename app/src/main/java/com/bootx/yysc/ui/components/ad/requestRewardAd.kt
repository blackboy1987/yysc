package com.bootx.yysc.ui.components.ad

import android.app.Activity
import android.content.Context
import com.bootx.yysc.config.Config
import com.youxiao.ssp.ad.bean.NextAdInfo
import com.youxiao.ssp.ad.bean.SSPAd
import com.youxiao.ssp.ad.core.AdClient
import com.youxiao.ssp.ad.listener.RewardVideoAdAdapter

/**
*激励视频广告：1028
*/
fun requestRewardAd(context: Context, onClose:(type:String)->Unit) {
    val adClient = AdClient(context as Activity)
    adClient.requestRewardAd(Config.REWARD_VIDEO_AD_ID, object : RewardVideoAdAdapter() {
        override fun onStatus(p0: Int, p1: Int, p2: Int, p3: String?) {
            onClose("onStatus")
        }

        override fun onNext(p0: NextAdInfo?) {
            onClose("onNext")
        }

        override fun loadRewardAdSuc(p0: SSPAd?) {
            onClose("loadRewardAdSuc")
        }

        override fun loadRewardAdFail(p0: String?) {
            onClose("loadRewardAdFail")
        }

        override fun loadRewardVideoSuc() {
            onClose("loadRewardVideoSuc")
        }

        override fun loadRewardVideoFail(p0: Int, p1: Int) {
            onClose("loadRewardVideoFail")
        }

        override fun startPlayRewardVideo() {
            onClose("startPlayRewardVideo")
        }

        override fun playRewardVideoAQuarter() {
            onClose("playRewardVideoAQuarter")
        }

        override fun playRewardVideoHalf() {
            onClose("playRewardVideoHalf")
        }

        override fun playRewardVideoThreeQuarters() {
            onClose("playRewardVideoThreeQuarters")
        }

        override fun playRewardVideoCompleted(p0: SSPAd?) {
            onClose("playRewardVideoCompleted")
        }

        override fun onReward(p0: SSPAd?, p1: Boolean, p2: MutableMap<String, Any>?) {
            onClose("onReward")
        }

        override fun getExtraInfo(p0: SSPAd?): Any {
            onClose("getExtraInfo")
            return super.getExtraInfo(p0)
        }

        override fun rewardVideoClick() {
            onClose("rewardVideoClick")
        }

        override fun rewardVideoButtonClick() {
            onClose("rewardVideoButtonClick")
        }

        override fun rewardVideoClosed() {
            onClose("rewardVideoClosed")
        }
    })
}