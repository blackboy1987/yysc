package com.bootx.yysc.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.PointLogEntity
import com.bootx.yysc.model.service.PointLogService
import com.bootx.yysc.util.SharedPreferencesUtils

class PointLogViewModel:ViewModel() {
    private val pointLogService = PointLogService.instance()

    // 是否还有更多数据
    var hasMore by mutableStateOf(true)
        private set

    // 列表加载状态
    var loading by mutableStateOf(false)
        private set

    // 列表数据
    var list = mutableListOf<PointLogEntity>()


    // 页码
    var pageNumber by mutableIntStateOf(1)
        private set


    suspend fun list(context: Context, pageNumber: Int, pageSize: Int) {
        if(!hasMore || loading){
            return
        }
        try {
            loading = true
            val res = pointLogService.list(SharedPreferencesUtils(context).get("token"),pageNumber, pageSize)
            if (res.code == 0 && res.data != null) {
                val tmpList = mutableListOf<PointLogEntity>()
                if (pageNumber != 1) {
                    tmpList.addAll(list)
                }
                tmpList.addAll(res.data)
                list = tmpList
                hasMore = res.data.size>=pageSize
                loading = false
                this.pageNumber += 1
            }
        }catch (e: Throwable){
            loading = false
        }
        loading = false
    }
}