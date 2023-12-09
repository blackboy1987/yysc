package com.bootx.yysc.util

import android.content.Context
import android.widget.Toast
import java.util.Date


object CommonUtils {

    fun toast(context: Context,msg: String) {
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
    }

    fun getDayInfo(time: Long): String{
        var now = Date().time
        var t1 = (now-time)/1000
        if(t1<60){
            return "一分钟内"
        }
        if(t1<60*60){
            return "一小时内"
        }
        if(t1<60*60*12){
            return "12小时内"
        }
        if(t1<60*60*24){
            return "一天内"
        }
        return "1天前"
    }
}