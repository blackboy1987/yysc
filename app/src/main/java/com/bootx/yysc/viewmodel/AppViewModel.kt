package com.bootx.yysc.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.CategoryEntity
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.model.service.CategoryService
import com.bootx.yysc.model.service.SoftService
import com.google.gson.Gson

class AppViewModel:ViewModel() {
    @SuppressLint("StaticFieldLeak")
    protected lateinit var context: Context
    open fun addContext(context: FragmentActivity) {
        this.context = context
    }

    open fun addContext(context: Context) {
        this.context = context
    }
    private val categoryService = CategoryService.instance()

    private val softService = SoftService.instance()

    var listLoaded by mutableStateOf(false)
        private set



    var pageNumber by mutableStateOf(1)
        private set

    var softListLoaded by mutableStateOf(false)
        private set



    var currentIndex by mutableStateOf(0)
        private set

    var categories = mutableListOf<CategoryEntity>()

    var softList by mutableStateOf(listOf<SoftEntity>())

    suspend fun fetchList(token: String,pageNumber: Int,pageSize: Int) {
        val res = categoryService.list(token,pageNumber,pageSize)
        val gson = Gson()
        if (res.code == 0) {
            val tmpList = mutableListOf<CategoryEntity>()
            tmpList.addAll(res.data)
            updateCurrentIndex(token,tmpList[0].id)
            categories = tmpList
            listLoaded = true
        } else {
            Log.e("fetchList",gson.toJson(res))
        }
    }

    suspend fun updateCurrentIndex(token: String,id: Int) {
        pageNumber = 1;
        softListLoaded = false
        currentIndex = id
        val res = softService.orderBy(token,1,20,"7",id)

        if (res.code == 0 && res.data != null) {
            val tmpList = mutableListOf<SoftEntity>()
            if (pageNumber != 1) {
                tmpList.addAll(softList)
            }
            tmpList.addAll(res.data)
            softList = tmpList
            softListLoaded = true
            pageNumber += 1
        }
    }

    suspend fun reload(token: String,) {
        softListLoaded = false
        pageNumber = 1
        val res = softService.orderBy(token,1,20,"7",currentIndex)
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

    suspend fun loadMore(token: String,) {
        softListLoaded = false
        val res = softService.orderBy(token,pageNumber,20,"7",currentIndex)
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