package com.bootx.yysc.ui.components.ad

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.bootx.yysc.R
import com.bootx.yysc.config.Config
import com.youxiao.ssp.ad.bean.SSPAd
import com.youxiao.ssp.ad.core.AdClient
import com.youxiao.ssp.ad.listener.AdLoadAdapter

/**
 *模板广告：8461
 */
@Composable
fun RequestExpressAd(context: Context) {
    val adClient = AdClient(context as Activity)
    AndroidView(factory = {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_template, null)
        val findViewById = view.findViewById<FrameLayout>(R.id.ad_layout)
        adClient.requestExpressAd(Config.TEMPLATE_AD_ID, object : AdLoadAdapter() {
            override fun onAdLoad(ad: SSPAd) {
                super.onAdLoad(ad)
                findViewById.removeAllViews()
                findViewById.addView(ad.view)
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
