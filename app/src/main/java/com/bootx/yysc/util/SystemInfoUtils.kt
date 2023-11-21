package com.bootx.yysc.util

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
}