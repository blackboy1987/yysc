package com.bootx.yysc.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.AppInfo
import com.bootx.yysc.util.AppInfoUtils

class TouGaoListViewModel : ViewModel() {

    @SuppressLint("QueryPermissionsNeeded")
    fun getAppList(context: Context): List<AppInfo> {
        return AppInfoUtils.getAppList(context)
    }
}