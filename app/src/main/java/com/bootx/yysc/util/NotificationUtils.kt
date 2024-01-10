package com.bootx.yysc.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bootx.yysc.R

object NotificationUtils {

    fun checkPermission(context: Context):Boolean{
        val manager = NotificationManagerCompat.from(context)
        return manager.areNotificationsEnabled()
    }

    @SuppressLint("MissingPermission")
    fun notification(context: Context){
        val manager = NotificationManagerCompat.from(context)
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                "Main channelId",
                "main channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }


        val builder = NotificationCompat.Builder(context, "appUpdate")
            .setSmallIcon(R.drawable.yx_logo_simple)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(
                NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        manager.notify(1011,builder.build())
    }

}