package com.bootx.yysc.util

import android.app.usage.StorageStats
import android.app.usage.StorageStatsManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.storage.StorageManager
import android.os.storage.StorageVolume
import com.bootx.yysc.model.entity.AppInfo
import java.io.IOException
import java.util.UUID


object AppInfoUtils {

    /**
     * 获取所有安装的App
     */
    fun getAppList(context: Context): List<AppInfo> {
        val appInfos = mutableListOf<AppInfo>()
        val packageManager = context.packageManager
        val installedApplications =
            packageManager.getInstalledApplications(PackageManager.MATCH_UNINSTALLED_PACKAGES)

        installedApplications.forEach { info ->
            run {
                appInfos.add(getAppInfo(context,info.packageName))
            }
        }
        return appInfos
    }

    /**
     * 判断app是否安装
     */
    fun isInstall(context: Context,packageName: String): Boolean{
        if(packageName.isEmpty() ){
            return false
        }
        val packageManager = context.packageManager
        try {
            val packageInfo =
                packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            return packageInfo !=null
        }catch (ignore: Exception){
        }

        return false
    }

    /**
     * 通过包名获取PackageInfo
     */
    fun getPackInfo(context: Context,packageName: String): PackageInfo? {
        if(packageName.isEmpty() ){
            return null
        }
        val packageManager = context.packageManager
        try {
            return packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        } catch (ignore: Exception) {
        }

        return null
    }

    /**
     * 通过包名获取app信息
     */
    fun getAppInfo(context: Context,packageName: String): AppInfo {
        val appInfo = AppInfo(
            appIcon = null,
            appName = "",
            versionName = "",
            packageName = packageName,
            versionCode = "",
        )
        if(packageName.isEmpty() ){
            return appInfo
        }
        val packageManager = context.packageManager
        try {
            val packageInfo =
                packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            appInfo.versionName = packageInfo.versionName
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.P){
                appInfo.versionCode = packageInfo.longVersionCode.toString()
            }else{
                appInfo.versionCode = packageInfo.versionCode.toString()
            }
            appInfo.appName = packageInfo.applicationInfo.loadLabel(packageManager).toString()
            appInfo.appIcon = packageInfo.applicationInfo.loadIcon(packageManager)

            val storageStatsManager =
                context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
            val storageManager =
                context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
            val storageVolumes: List<StorageVolume> = storageManager.getStorageVolumes()

            for (item in storageVolumes) {
                val uuidStr = item.uuid
                var uuid: UUID?
                uuid = if (uuidStr == null) {
                    StorageManager.UUID_DEFAULT
                } else {
                    UUID.fromString(uuidStr)
                }
                val uid: Int = getUid(context, packageName)
                //通过包名获取uid
                var storageStats: StorageStats? = null
                try {
                    storageStats = storageStatsManager.queryStatsForUid(uuid, uid)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                //缓存大小
                storageStats!!.cacheBytes
                //数据大小
                storageStats!!.dataBytes
                //应用大小
                appInfo.appBytes = storageStats!!.appBytes
                //应用的总大小
                storageStats!!.cacheBytes + storageStats!!.dataBytes + storageStats!!.appBytes
            }


        } catch (ignore: Exception) {
        }

        return appInfo
    }

    private fun getUid(context: Context, packageName: String): Int {
        try {
            return context.packageManager.getApplicationInfo(
                packageName,
                PackageManager.GET_META_DATA
            ).uid
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return -1
    }
}