package com.bootx.yysc.ui.components.ad

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.bootx.yysc.R
import com.bootx.yysc.config.Config
import com.youxiao.ssp.ad.bean.SSPAd
import com.youxiao.ssp.ad.core.AdClient
import com.youxiao.ssp.ad.listener.AdLoadAdapter

/**
 *横幅广告：1983 OK
 */
@Composable
fun RequestBannerAd(context: Context) {
    val adClient = AdClient(context as Activity)
    AndroidView(factory = {
        val view = LayoutInflater.from(it).inflate(R.layout.activity_banner, null)
        val findViewById = view.findViewById<FrameLayout>(R.id.ad_layout)
        adClient.requestBannerAd(findViewById, Config.BANNER_AD_ID, object : AdLoadAdapter() {
            override fun onAdLoad(ad: SSPAd) {
                Log.e("requestBannerAd onAdLoad", "onAdLoad: $ad")
                super.onAdLoad(ad)
            }

            override fun onError(i: Int, s: String) {
                super.onError(i, s)
            }

            override fun onAdShow(ad: SSPAd?) {
                super.onAdShow(ad)
            }
        })
        view
    })
}
