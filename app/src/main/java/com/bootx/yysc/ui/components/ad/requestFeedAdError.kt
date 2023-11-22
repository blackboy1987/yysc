package com.bootx.yysc.ui.components.ad

import android.content.Context
import androidx.compose.runtime.Composable


/**
 *信息流广告：2351
 */
@Composable
fun RequestFeedAd(context: Context, onClose:(type:String)->Unit) {
   /* var status: String = ""
    val adClient = AdClient(context as Activity)

    AndroidView(factory = {
        val view = LayoutInflater.from(it).inflate(R.layout.activity_fee, null)
        val closeAdTV = view.findViewById<TextView>(R.id.close_ad_tv)
        closeAdTV.setOnClickListener {
            if (tipDialog != null && tipDialog.isShowing()) {
                tipDialog.dismiss()
            }
            val items = arrayOf("不感兴趣", "反垃圾内容", "屏蔽")
            tipDialog = AlertDialog.Builder(context)
                .setItems(items) { dialog, which ->
                    Toast.makeText(context, "已提交反馈", Toast.LENGTH_SHORT).show()
                    this@FeedActivity.finish()
                }.create()
            tipDialog.show()
        }

    })*/
}