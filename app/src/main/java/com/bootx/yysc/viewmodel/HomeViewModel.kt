package com.bootx.yysc.viewmodel

import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.model.service.SoftService
import com.google.gson.Gson

class HomeViewModel:ViewModel() {
    private val softService = SoftService.instance()

    suspend fun orderBy(pageNumber: Int,pageSize: Int,orderBy: String): List<SoftEntity> {
        val gson = Gson()
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
}