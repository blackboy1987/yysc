package com.bootx.yysc.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.service.ComplaintsService
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.util.UploadUtils
import github.leavesczy.matisse.MediaResource

class ComplaintsViewModel:ViewModel() {

    var complaintsService = ComplaintsService.instance()

    var progress by mutableStateOf(0)
        private set

    var msg by mutableStateOf("")
        private set

    @RequiresApi(Build.VERSION_CODES.Q)
    suspend fun save(context: Context, list: List<MediaResource>, type: Int, reason: String,softId: String):Boolean {
        progress = 0
        val rate = 100/list.size

        val urls = arrayOfNulls<String>(list.size)
        // 因为只支持单个文件上传，所以。得循环上传
        list.forEachIndexed { index, item->
            UploadUtils.uri2File(item.uri, context)?.let {
                urls[index] = UploadUtils.uploadImage(SharedPreferencesUtils(context).get("token"), it)
                progress += rate
                msg = "正在上传${index+1}张图片"
            }
        }
        // 保存数据
        try {
            val res = complaintsService.save(SharedPreferencesUtils(context).get("token"),type,reason,urls.joinToString(","),softId)
            CommonUtils.toast(context,res.msg)
            return res.code==0
        }catch (e:Exception){
            CommonUtils.toast(context,e.toString());
        }
        return false
    }
}