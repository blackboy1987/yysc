package com.bootx.yysc.model.entity

import android.graphics.drawable.Drawable

data class AppInfo(
    val appIcon: Drawable,
    val appName: String,
    var appVersion: String,
    val packageName: String,
)