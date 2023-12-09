package com.bootx.yysc.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.service.FanEntity
import com.bootx.yysc.model.service.FanService

class FanViewModel:ViewModel() {

    var fanService: FanService = FanService.instance()

    var list by mutableStateOf(listOf<FanEntity>())

    suspend fun list(token: String,type: Int) {
        val res = fanService.list(token,type)
        if(res.code==0){
            list = res.data
        }
    }

    suspend fun delete(token: String,id: Int,type:Int) {
        val res = fanService.delete(token,id,type)
        if(res.code==0){
            list = list.filter { item->item.id!=id }
        }
    }
}