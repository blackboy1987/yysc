package com.bootx.yysc.ui.components

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.bootx.yysc.FeedActivity
import com.bootx.yysc.R
import com.bootx.yysc.util.ActivityStackManager
import com.youxiao.ssp.ad.bean.SSPAd
import com.youxiao.ssp.ad.core.AdClient
import com.youxiao.ssp.ad.listener.AdLoadAdapter

@Composable
fun BannerAd(context: Context) {
    val adClient = AdClient(context as Activity)
    AndroidView(factory = {
        val view = LayoutInflater.from(it).inflate(R.layout.activity_banner, null)
        val findViewById = view.findViewById<FrameLayout>(R.id.ad_layout)
        adClient.requestBannerAd(findViewById, "1983", object : AdLoadAdapter() {
            override fun onAdLoad(ad: SSPAd) {
                Log.e("requestBannerAd onAdLoad", "onAdLoad: $ad")
                super.onAdLoad(ad)
            }

            override fun onError(i: Int, s: String) {
                super.onError(i, s)
                Toast.makeText(
                    context,
                    "横幅广告加载失败:$s",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("requestBannerAd onError", "onAdLoad: $s")
            }

            override fun onAdShow(ad: SSPAd?) {
                super.onAdShow(ad)
                Log.e("requestBannerAd onAdShow", "onAdLoad: $ad")
                super.onAdLoad(ad)
            }
        })
        view

    })
}

@Composable
fun FeedAd(context: Context) {
    AndroidView(factory = {
        val view1 = LayoutInflater.from(context).inflate(R.layout.activity_fee, null)
        val closeAdTV = view1.findViewById<TextView>(R.id.close_ad_tv)
        var tipDialog: AlertDialog? = null
        closeAdTV.setOnClickListener(View.OnClickListener {
            if (tipDialog?.isShowing() == true) {
                tipDialog!!.dismiss()
            }
            val items = arrayOf("不感兴趣", "反垃圾内容", "屏蔽")
            val feedActivity = ActivityStackManager.getActivity(FeedActivity::class.java)
            tipDialog = AlertDialog.Builder(feedActivity)
                .setItems(items) { dialog, which ->
                    Toast.makeText(feedActivity, "已提交反馈", Toast.LENGTH_SHORT).show()
                    feedActivity
                }.create()
            tipDialog?.show()
        })


        view1
    })
}