package com.bootx.yysc.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.SignInEntity
import com.bootx.yysc.model.service.SettingEntity
import com.bootx.yysc.model.service.SettingService
import com.bootx.yysc.model.service.SignInService
import com.bootx.yysc.util.SharedPreferencesUtils

class SettingViewModel:ViewModel() {
    private val settingService  = SettingService.instance()

    suspend fun initApp(context: Context): SettingEntity{
        val setting = settingService.setting(SharedPreferencesUtils(context).get("token"))
        return setting.data
    }
}