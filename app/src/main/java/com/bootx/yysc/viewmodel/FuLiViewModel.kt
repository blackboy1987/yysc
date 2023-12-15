package com.bootx.yysc.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.FuLiEntity
import com.bootx.yysc.model.service.FuLiService
import com.bootx.yysc.util.CommonUtils

class FuLiViewModel:ViewModel() {

    private var fuLiService = FuLiService.instance()

    var list by mutableStateOf(listOf<FuLiEntity>())

    var loading by mutableStateOf(false)

    suspend fun list(context:Context) {
        val res = fuLiService.list(CommonUtils.getToken(context))
        if(res.code==0){
            list = res.data
        }else{
            Log.e("fuLiService", "list: ${res.msg}", )
        }
    }
}