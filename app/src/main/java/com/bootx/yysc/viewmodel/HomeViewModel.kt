package com.bootx.yysc.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.AppVersion
import com.bootx.yysc.model.service.AppVersionService
import com.bootx.yysc.util.AppInfoUtils

class HomeViewModel:ViewModel() {
    private val appVersionService = AppVersionService.instance()

    /**
     * 检测更新
     */
    suspend fun checkUpdate(context: Context): List<AppVersion> {
        val appInfo = AppInfoUtils.getAppInfo(context, "com.bootx.yysc")
        val check = appVersionService.check(appInfo.versionCode)
        Log.e("checkUpdate12345", "checkUpdate: ${check.data}", )
        return check.data?: listOf()
    }
}