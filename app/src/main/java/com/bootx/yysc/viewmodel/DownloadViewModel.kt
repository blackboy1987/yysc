package com.bootx.yysc.viewmodel

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import com.azhon.appupdate.config.Constant
import com.azhon.appupdate.listener.OnDownloadListener
import com.azhon.appupdate.manager.DownloadManager
import com.azhon.appupdate.util.NotificationUtil
import com.bootx.yysc.R
import com.bootx.yysc.model.service.SoftService
import com.bootx.yysc.repository.DataBase
import com.bootx.yysc.repository.entity.DownloadManagerEntity
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.util.SharedPreferencesUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File


class DownloadViewModel:ViewModel() {

    private val softService = SoftService.instance()

    suspend fun download(context: Context,id: Int) {
        // 接口请求下载地址
        val downloadInfo = softService.download(SharedPreferencesUtils(context).get("token"), id)
        val data = downloadInfo.data
        if (downloadInfo.code == 0 && data != null && data.downloadUrl.isNotEmpty()) {
            val manager = DownloadManager.Builder(context as Activity).run {
                apkUrl(data.downloadUrl).smallIcon(R.drawable.logo)
                    .showNotification(false)
                    .showBgdToast(false)
                    .showNewerToast(false)
                apkName(data.name + ".apk").onDownloadListener(onDownloadListener = object :
                    OnDownloadListener {
                    override fun cancel() {
                        NotificationUtil.cancelNotification(context)
                    }
                    override fun done(apk: File) {
                        Log.e("DownloadViewModel", "done: ${apk.path}", )
                        NotificationUtil.showDoneNotification(
                            context, R.drawable.network_error,
                            "${data.name}下载完成,点击安装",
                            "点击安装${data.name}",
                            Constant.AUTHORITIES!!, apk
                        )
                        CoroutineScope(Dispatchers.IO).launch {
                            // 写入到数据库
                            try {
                                DataBase.getDb(context)?.getDownloadManagerDao()?.insert(
                                    DownloadManagerEntity(
                                        id = id,
                                        name = data.name,
                                        logo = data.logo,
                                        packageName = data.packageName ?: "",
                                        path = apk.path,
                                    )
                                )
                            }catch (e:Exception){
                              e.stackTrace
                            }
                        }

                    }

                    override fun downloading(max: Int, progress: Int) {
                        Log.e("DownloadViewModel", "downloading: ${max}:${progress}", )
                        val curr = (progress / max.toDouble() * 100.0).toInt()
                        val content = if (curr < 0) "" else "$curr%"
                        SharedPreferencesUtils(context).set("download_$id",content)
                        NotificationUtil.showProgressNotification(
                            context, R.drawable.network_error,
                            "${data.name}下载中...",
                            content, if (max == -1) -1 else 100, curr
                        )
                    }

                    override fun error(e: Throwable) {
                        Log.e("DownloadViewModel", "error: ${e.toString()}", )
                        NotificationUtil.showErrorNotification(
                            context, R.drawable.network_error,
                            "${data.name} 下载出错",
                            "点击继续下载",
                        )
                    }

                    override fun start() {
                        Log.e("DownloadViewModel", "start: start", )
                        NotificationUtil.showNotification(
                            context, R.drawable.network_error,
                            "开始下载 ${data.name}",
                            "可稍后查看 ${data.name} 下载进度"
                        )
                    }
                })
                apkVersionName(data.versionName)
                apkSize(data.size)
                apkDescription("更新描述信息(取服务端返回数据)")
                build()
            }
            manager.download()
        }else{
            CommonUtils.toast(context, "暂无下载地址")
        }
    }

    suspend fun install(context: Context,path: String) {
        Log.d("install", "install: $path")
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setDataAndType(
            Uri.parse("file://" + path),
            "application/vnd.android.package-archive"
        )
        context.startActivity(intent)
    }
}