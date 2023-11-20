package com.bootx.yysc.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.PointLogEntity
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.model.service.PointLogService
import com.bootx.yysc.model.service.SoftService
import com.google.gson.Gson

class PointLogViewModel:ViewModel() {
    private val pointLogService = PointLogService.instance()

    // 是否还有更多数据
    var hasMore by mutableStateOf(false)
        private set

    // 列表加载状态
    var loading by mutableStateOf(false)
        private set

    // 列表数据
    var list = mutableListOf<PointLogEntity>()


    // 页码
    var pageNumber by mutableIntStateOf(1)
        private set


    suspend fun list(pageNumber: Int,pageSize: Int): List<PointLogEntity> {
        try {
            loading = true
            val res = pointLogService.list(pageNumber, pageSize)
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
                return tmpList
            }
        }catch (e: Throwable){
            loading = false
            return listOf()
        }
        loading = false
        return listOf()
    }
}