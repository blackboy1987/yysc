package com.bootx.yysc.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.TouGaoEntity


class TouGaoViewModel : ViewModel() {


    var touGaoInfo by mutableStateOf(TouGaoEntity())
        private set

    fun updateTouGaoInfo(value: Any,key: String): TouGaoEntity {
        when(key){
            "categoryId0"->touGaoInfo.categoryId0 = value as Int
        }

        return touGaoInfo
    }
}