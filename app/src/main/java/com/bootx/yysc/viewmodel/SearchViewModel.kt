package com.bootx.yysc.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.CategoryEntity
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.model.service.FanService
import com.bootx.yysc.model.service.LoadAdEntity
import com.bootx.yysc.model.service.RewardService
import com.bootx.yysc.model.service.SearchData
import com.bootx.yysc.model.service.SearchService
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.util.SharedPreferencesUtils

class SearchViewModel:ViewModel() {

    private val searchService = SearchService.instance()

    private val fanService = FanService.instance()

    var pageNumber by mutableStateOf(1)
        private set

    var pageSize = 20
        private set

    var loading by mutableStateOf(true)
        private set

    var typeList = mutableListOf<String>()

    var list by mutableStateOf(listOf<SearchData>())

    var hasMore by mutableStateOf(true)
        private set

    suspend fun search(context: Context,keywords: String,type: Int,pageNumber: Int) {
        try {
            val res = searchService.search(SharedPreferencesUtils(context).get("token"),keywords,type,pageNumber,pageSize)
            if (res.code == 0) {
                val tmpList = mutableListOf<SearchData>()
                if (pageNumber != 1) {
                    tmpList.addAll(list)
                }
                tmpList.addAll(res.data)
                list = tmpList
                this.hasMore = res.data.size>=pageSize
                if(this.hasMore){
                    this.pageNumber = pageNumber+1
                }
            }
        }catch (e: Throwable){
            CommonUtils.toast(context,"${e.message}")
        }
    }
    suspend fun type(context: Context) {
        try {
            val res = searchService.type(SharedPreferencesUtils(context).get("token"))
            val tmpList = mutableListOf<String>()
            tmpList.addAll(res.data)
            typeList = tmpList
        }catch (e: Throwable){
            CommonUtils.toast(context,"${e.message}")
        }
    }

    suspend fun userType(context: Context,id: Int, type: Int) {
        if(type==0){
            fanService.add(SharedPreferencesUtils(context).get("token"),id)
            val tmpList = mutableListOf<SearchData>()
            tmpList.addAll(list)
            list = tmpList.map { item ->
                if (item.id == id) {
                    item.isConcern = 1
                    item
                } else item
            }
        }else if(type==1){
            fanService.delete(SharedPreferencesUtils(context).get("token"),id,0)
            val tmpList = mutableListOf<SearchData>()
            tmpList.addAll(list)
            list = tmpList.map { item ->
                if (item.id == id) {
                    item.isConcern = 0
                    item
                } else item
            }
        }
    }
}