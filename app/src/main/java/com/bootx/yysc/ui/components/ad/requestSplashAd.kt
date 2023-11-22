package com.bootx.yysc.ui.components.ad

import android.app.Activity
import android.content.Context
import com.youxiao.ssp.ad.bean.SSPAd
import com.youxiao.ssp.ad.core.AdClient
import com.youxiao.ssp.ad.listener.AdLoadAdapter

/**
 * 开屏广告: 3561
 */
fun requestSplashAd(context: Context) {
    /*val adClient = AdClient(context as Activity)
    adClient.requestSplashAd(adContainer, "3561", object : AdLoadAdapter() {
        fun onError(var1: Int,error: String) {
            super.onError(var1,error)
            //获取广告失败，跳转主页
            gotoMainActivity()
        }

        override fun onAdDismiss(ad: SSPAd) {
            super.onAdDismiss(ad)
            //广告关闭（开屏广告展示时间到或用户点击跳转），跳转主页
            gotoMainActivity()
        }
    })*/

}