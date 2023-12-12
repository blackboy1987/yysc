package com.bootx.yysc.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.CategoryEntity
import com.bootx.yysc.model.service.SoftIconLogService
import com.bootx.yysc.repository.DataBase
import com.bootx.yysc.repository.entity.DownloadManagerEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DownloadManagerViewModel:ViewModel() {

    var list = mutableListOf<DownloadManagerEntity>()
    suspend fun list(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val downloadManagerDao = DataBase.getDb(context)?.getDownloadManagerDao()
            if (downloadManagerDao != null) {
                list.addAll(downloadManagerDao.getAll())
            }
        }

    }
}