package com.bootx.yysc.model.entity

import android.graphics.drawable.Drawable

data class AppInfo(
    var appIcon: Drawable?,
    var appName: String,
    var versionName: String,
    val packageName: String,
    var versionCode: String,
    var appBytes: Long?=0L,
)