package com.bootx.yysc.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.ActivityEntity
import com.bootx.yysc.model.entity.AppVersion
import com.bootx.yysc.model.entity.CarouselEntity
import com.bootx.yysc.model.entity.CategoryEntity
import com.bootx.yysc.model.entity.HomeCenterBar
import com.bootx.yysc.model.service.AppVersionService
import com.bootx.yysc.model.service.HomeService
import com.bootx.yysc.util.AppInfoUtils
import com.google.gson.Gson

class HomeViewModel:ViewModel() {
    private val appVersionService = AppVersionService.instance()
    private val homeService = HomeService.instance()



    var homeCenterBarList = mutableListOf<HomeCenterBar>()


    /**
     * 检测更新
     */
    suspend fun checkUpdate(token: String,context: Context): List<AppVersion> {
        val appInfo = AppInfoUtils.getAppInfo(context, "com.bootx.yysc")
        val check = appVersionService.check(token,appInfo.versionCode,appInfo.versionName)
        Log.e("checkUpdate12345", "checkUpdate: ${check.data}", )
        return check.data?: listOf()
    }

    suspend fun homeCenterBar(token: String,): List<HomeCenterBar> {
        val res = homeService.homeCenterBar(token)
        if (res.code == 0 && res.data != null) {
            return res.data
        }
        return listOf<HomeCenterBar>()
    }

    suspend fun activity(token: String,): List<ActivityEntity> {
        val res = homeService.activity(token)
        if (res.code == 0 && res.data != null) {
            return res.data
        }
        return listOf<ActivityEntity>()
    }
}