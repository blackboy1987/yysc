package com.bootx.yysc.ui.components.ad

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.bootx.yysc.R
import com.bootx.yysc.model.entity.AdConfig
import com.bootx.yysc.util.SharedPreferencesUtils
import com.google.gson.Gson
import com.youxiao.ssp.ad.bean.SSPAd
import com.youxiao.ssp.ad.core.AdClient
import com.youxiao.ssp.ad.listener.AdLoadAdapter

/**
 *模板视频信息流：8517
 */
@Composable
fun RequestExpressDrawFeedAd(context: Context) {
    val adClient = AdClient(context as Activity)


    AndroidView(factory = {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_video_feed, null)
        val findViewById = view.findViewById<FrameLayout>(R.id.ad_flayout)
        val gson = Gson()
        val get = SharedPreferencesUtils(context).get("adConfig")
        val adConfig = gson.fromJson(get, AdConfig::class.java)
        adClient.requestExpressDrawFeedAd(adConfig.videoFeedAdId, object : AdLoadAdapter() {
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
