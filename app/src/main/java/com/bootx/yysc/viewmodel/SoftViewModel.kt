package com.bootx.yysc.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.CategoryEntity
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.model.service.CategoryService
import com.bootx.yysc.model.service.SoftService
import com.google.gson.Gson

class SoftViewModel:ViewModel() {
    private val softService = SoftService.instance()

    var listLoaded by mutableStateOf(false)
        private set
    var listLoadedErrorData by mutableStateOf("")
        private set

    var softListLoaded by mutableStateOf(false)
        private set

    var softList = mutableListOf<SoftEntity>()

    var pageNumber by mutableIntStateOf(1)
        private set


    suspend fun orderBy(pageNumber: Int,pageSize: Int,orderBy: String): List<SoftEntity> {
        try {
            val res = softService.orderBy(pageNumber, pageSize,orderBy)
            if (res.code == 0 && res.data != null) {
                val tmpList = mutableListOf<SoftEntity>()
                tmpList.addAll(res.data)
                return tmpList;
            }
        }catch (e: Throwable){
            return listOf()
        }
        return listOf()
    }

    suspend fun reload(orderBy: String) {
        softListLoaded = false
        pageNumber = 1
        val res = softService.orderBy(1,20,orderBy)
        val gson = Gson()
        if (res.code == 0 && res.data != null) {
            val tmpList = mutableListOf<SoftEntity>()
            tmpList.addAll(res.data)
            softList = tmpList
            softListLoaded = true
            pageNumber += 1
        } else {
            Log.e("fetchList",gson.toJson(res))
        }
    }

    suspend fun loadMore(orderBy: String) {
        softListLoaded = false
        val res = softService.orderBy(pageNumber,20,orderBy)
        val gson = Gson()
        if (res.code == 0 && res.data != null) {
            val tmpList = mutableListOf<SoftEntity>()
            if (pageNumber != 1) {
                tmpList.addAll(softList)
            }
            tmpList.addAll(res.data)
            softList = tmpList
            softListLoaded = true
            pageNumber += 1
        } else {
            Log.e("fetchList",gson.toJson(res))
        }
    }
}