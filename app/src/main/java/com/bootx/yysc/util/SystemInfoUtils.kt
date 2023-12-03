package com.bootx.yysc.util

import android.content.Context
import android.os.Build

object SystemInfoUtils {

    /**
     * 获取所有安装的App
     */
    fun getSystemInfo(): Unit {
        val sdkInt = Build.VERSION.SDK_INT
        val baseOs = Build.VERSION.BASE_OS
        val codename = Build.VERSION.CODENAME
    }


    fun getScreenWidth(context:Context): Int{
        return context.resources.displayMetrics.widthPixels
    }

    fun getScreenHeight(context:Context): Int{
        return context.resources.displayMetrics.heightPixels
    }
    fun getScreenRatio(context:Context): String{
        return getScreenWidth(context).toString()+ "*"+ getScreenHeight(context).toString()
    }

    fun dp2px(dpValue: Float,context: Context): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue*scale + 0.5f).toInt()
    }

    fun px2dp(pxValue: Int, context: Context): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue/scale + 0.5f).toInt()
    }

}