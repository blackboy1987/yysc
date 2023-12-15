package com.bootx.yysc.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.QunZuEntity
import com.bootx.yysc.model.service.QunZuService
import com.bootx.yysc.util.CommonUtils

class QunZuViewModel:ViewModel() {

    private var qunZuService = QunZuService.instance()

    var list by mutableStateOf(listOf<QunZuEntity>())

    var loading by mutableStateOf(false)

    suspend fun list(context:Context) {
        val res = qunZuService.list(CommonUtils.getToken(context))
        if(res.code==0){
            list = res.data
        }else{
            Log.e("qunZuService", "list: ${res.msg}", )
        }
    }
}