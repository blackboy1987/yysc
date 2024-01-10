package com.bootx.yysc.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.AppVersion
import com.bootx.yysc.model.service.AppVersionService
import com.bootx.yysc.model.service.HomeData
import com.bootx.yysc.model.service.HomeService
import com.bootx.yysc.util.AppInfoUtils
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.util.SharedPreferencesUtils
import com.google.gson.Gson

class HomeViewModel:ViewModel() {
    private val appVersionService = AppVersionService.instance()
    private val homeService = HomeService.instance()

    var homeData by mutableStateOf(HomeData())

    var loading by mutableStateOf(false)
        private set

    /**
     * 检测更新
     */
    suspend fun checkUpdate(token: String,context: Context): List<AppVersion> {
        val appInfo = AppInfoUtils.getAppInfo(context, "com.bootx.yysc")
        val check = appVersionService.check(token,appInfo.versionCode,appInfo.versionName)
        Log.e("checkUpdate12345", "checkUpdate: ${check.data}", )
        return check.data?: listOf()
    }

    suspend fun load(context:Context){
        loading = true
        val res = homeService.load(SharedPreferencesUtils(context).get("token"))
        if (res.code == 0) {
            homeData = res.data
        }else{
            CommonUtils.toast(context,res.msg)
        }
        loading = false
    }
}








