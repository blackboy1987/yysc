package com.bootx.yysc.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.CategoryEntity
import com.bootx.yysc.model.entity.TouGaoEntity
import com.bootx.yysc.model.service.TouGaoService


class TouGaoViewModel : ViewModel() {

    var touGaoService = TouGaoService.instance()

    var categories by mutableStateOf(listOf<CategoryEntity>())

    var touGaoInfo by mutableStateOf(TouGaoEntity())
        private set

    fun updateTouGaoInfo(value: Any,key: String): TouGaoEntity {
        when(key){
            "categoryId0"->touGaoInfo.categoryId0 = value as Int
        }

        return touGaoInfo
    }

    suspend fun categoryList(){
        val res = touGaoService.category();
        categories = res.data
        Log.e("categoryList", "categoryList: $res.data")
    }
}