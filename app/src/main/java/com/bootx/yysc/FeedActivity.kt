package com.bootx.yysc

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bootx.yysc.util.ActivityStackManager
import com.bumptech.glide.Glide
import com.youxiao.ssp.ad.bean.SSPAd
import com.youxiao.ssp.ad.core.AdClient
import com.youxiao.ssp.ad.listener.AdLoadAdapter

class FeedActivity() : AppCompatActivity() {
    // 关闭广告
    private lateinit var closeAdTV: TextView

    // 关闭广告提示
    private lateinit var tipDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        ActivityStackManager.pushActivity(this@FeedActivity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fee)
        closeAdTV = findViewById(R.id.close_ad_tv)
        closeAdTV.setOnClickListener(View.OnClickListener {
            if (tipDialog.isShowing()) {
                tipDialog.dismiss()
            }
            val items = arrayOf("不感兴趣", "反垃圾内容", "屏蔽")
            tipDialog = AlertDialog.Builder(this@FeedActivity)
                .setItems(items) { dialog, which ->
                    Toast.makeText(this@FeedActivity, "已提交反馈", Toast.LENGTH_SHORT).show()
                    finish()
                }.create()
            tipDialog.show()
        })
        requestFeedAd()
    }

    /**
     * 加载信息流广告
     */
    private fun requestFeedAd() {
        //初始化广告客户端实例

        //初始化广告客户端实例
        val adClient = AdClient(this)
        val feedAdLayout: View = findViewById(R.id.feed_ad_layout)
        // 信息流广告标题
        // 信息流广告标题
        val feedAdTitleTV: TextView = findViewById(R.id.title_tv)
        // 信息流广告描述
        // 信息流广告描述
        val feedAdDescTV: TextView = findViewById(R.id.desc_tv)
        // 信息流广告图片
        // 信息流广告图片
        val feedAdIV: ImageView = findViewById(R.id.feed_img_iv)
        adClient.requestFeedAd("2351", object : AdLoadAdapter() {
            override fun onAdLoad(ad: SSPAd) {
                super.onAdLoad(ad)
                feedAdLayout.visibility = View.VISIBLE
                //获取到信息流广告信息，自己渲染展示
                Glide.with(this@FeedActivity).load(ad.img).into(feedAdIV)
                // 自主显示广告
                feedAdTitleTV.text = ad.title
                feedAdDescTV.text = ad.desc
                //注册展示信息流广告的控件（***** 非常重要，影响收益 *****）
                adClient.registerView(feedAdLayout, ad, AdLoadAdapter())
            }
        })
    }
}