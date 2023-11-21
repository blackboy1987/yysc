package com.bootx.yysc.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.AppInfo
import com.bootx.yysc.util.AppInfoUtils
import com.youxiao.ssp.ad.core.AdClient


class TouGaoListViewModel : ViewModel() {

    @SuppressLint("QueryPermissionsNeeded")
    fun getAppList(context: Context): List<AppInfo> {
        return AppInfoUtils.getAppList(context)
    }
}