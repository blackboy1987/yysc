package com.bootx.yysc.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.model.service.FanEntity
import com.bootx.yysc.model.service.FanService

class FanViewModel : ViewModel() {

    var fanService: FanService = FanService.instance()

    var list by mutableStateOf(listOf<FanEntity>())

    var hasMore by mutableStateOf(true)

    var loading by mutableStateOf(false)
    var pageNumber by mutableStateOf(1)
        private set
    var pageSize by mutableStateOf(20)
        private set


    private suspend fun fetch(token: String, type: Int, pageNumber: Int) {
        if (hasMore && !loading) {
            loading = true
            val res = fanService.list(token, type, pageNumber, pageSize)
            if (res.code == 0) {
                val tmpList = mutableListOf<FanEntity>()
                if (pageNumber != 1) {
                    tmpList.addAll(list)
                }
                tmpList.addAll(res.data)
                list = tmpList
                if (res.data.size == pageSize) {
                    hasMore = true
                    this.pageNumber += 1
                } else {
                    hasMore = false
                }
            }
            loading = false
        }
    }

    suspend fun list(token: String, type: Int) {
        hasMore = true
        fetch(token, type, 1)
    }

    suspend fun loadMore(token: String, type: Int) {
        fetch(token, type, pageNumber)
    }

    suspend fun delete(token: String, id: Int, type: Int) {
        val res = fanService.delete(token, id, type)
        if (res.code == 0) {
            list = list.filter { item -> item.id != id }
        }
        list(token,type)
    }

    suspend fun add(token: String, id: Int, type: Int) {
        val res = fanService.add(token, id)
        if (res.code == 0) {
            list = list.map { item ->
                if (item.id == id) {
                    item.count = 1
                    item
                } else item
            }
        }
        list(token,type)
    }
}