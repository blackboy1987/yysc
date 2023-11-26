package com.bootx.yysc.util

import android.content.Context
import android.widget.Toast


object CommonUtils {

    /**
     * 获取所有安装的App
     */
    fun toast(context: Context,msg: String) {
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
    }
}