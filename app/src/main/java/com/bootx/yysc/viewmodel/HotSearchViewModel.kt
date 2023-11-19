package com.bootx.yysc.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.HotSearchEntity
import com.bootx.yysc.model.service.HotSearchService

class HotSearchViewModel:ViewModel() {
    private val hotSearchService = HotSearchService.instance()

    var listLoaded by mutableStateOf(false)
        private set

    var list = mutableListOf<HotSearchEntity>()



    suspend fun fetchList(): List<HotSearchEntity> {
        try {
            listLoaded = false
            val res = hotSearchService.fetchList()
            if (res.code == 0 && res.data != null) {
                val tmpList = mutableListOf<HotSearchEntity>()
                tmpList.addAll(res.data)
                list = tmpList
                listLoaded = true
                return tmpList;
            }
        }catch (e: Throwable){
            return listOf()
        }
        return listOf()
    }
}