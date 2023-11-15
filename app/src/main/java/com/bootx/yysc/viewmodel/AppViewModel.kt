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

class AppViewModel:ViewModel() {

    private val categoryService = CategoryService.instance()

    private val softService = SoftService.instance()

    var listLoaded by mutableStateOf(false)
        private set

    var softListLoaded by mutableStateOf(false)
        private set



    var currentIndex by mutableStateOf(0)
        private set

    var categories = mutableListOf<CategoryEntity>()

    var softList = mutableListOf<SoftEntity>()

    suspend fun fetchList(pageNumber: Int,pageSize: Int) {
        val res = categoryService.list(pageNumber,pageSize)
        val gson = Gson()
        if (res.code == 0 && res.data != null) {
            val tmpList = mutableListOf<CategoryEntity>()
            tmpList.addAll(res.data)
            updateCurrentIndex(tmpList[0].id)
            categories = tmpList
            listLoaded = true
        } else {
            Log.e("fetchList",gson.toJson(res))
        }
    }

    suspend fun updateCurrentIndex(id: Int) {
        softListLoaded = false
        currentIndex = id
        val res = softService.list(id,1,20)
        val gson = Gson()
        if (res.code == 0 && res.data != null) {
            val tmpList = mutableListOf<SoftEntity>()
            tmpList.addAll(res.data)
            softList = tmpList
            softListLoaded = true
        } else {
            Log.e("fetchList",gson.toJson(res))
        }
    }
}