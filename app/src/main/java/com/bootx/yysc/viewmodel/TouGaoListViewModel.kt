package com.bootx.yysc.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.AppInfo
import com.bootx.yysc.model.entity.CategoryEntity

class TouGaoListViewModel : ViewModel() {

    @SuppressLint("QueryPermissionsNeeded")
    fun getAppList(context: Context): List<AppInfo> {
        val appInfos = mutableListOf<AppInfo>()
        val packageManager = context.packageManager
        val installedApplications =
            packageManager.getInstalledApplications(PackageManager.MATCH_UNINSTALLED_PACKAGES)

        installedApplications.forEach { info ->
            run {
                val loadIcon = info.loadIcon(packageManager);
                val appName = info.loadLabel(packageManager);
                val packageName = info.packageName
                val appInfo: AppInfo = AppInfo(
                    appIcon = loadIcon,
                    appName = appName.toString(),
                    appVersion = "1.0.0",
                    packageName = packageName,
                )
                try {
                    val packageInfo = packageManager.getPackageInfo(packageName, 0)
                    val version = packageInfo.versionName
                    appInfo.appVersion = version
                } catch (e: Exception) {
                    println(e.message)
                }
                appInfos.add(appInfo)
            }
        }
        return appInfos
    }
}