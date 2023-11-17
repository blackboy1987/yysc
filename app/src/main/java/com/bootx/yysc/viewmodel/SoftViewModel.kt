package com.bootx.yysc.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
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

    suspend fun fetchList(pageNumber: Int,pageSize: Int,orderBy: String) {
        val gson = Gson()
        try {
            val res = softService.orderBy(1,20,orderBy)
            if (res.code == 0 && res.data != null) {
                val tmpList = mutableListOf<SoftEntity>()
                tmpList.addAll(res.data)
                softList = tmpList
                softListLoaded = true
            } else {
                Log.e("fetchList",gson.toJson(res))
            }
        }catch (e: Throwable){
            listLoadedErrorData = ""
            Log.e("fetchList", gson.toJson(e), )
        }
    }

}