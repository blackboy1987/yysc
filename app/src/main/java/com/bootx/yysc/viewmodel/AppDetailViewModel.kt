package com.bootx.yysc.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.SoftDetailEntity
import com.bootx.yysc.model.service.SoftIconLogService
import com.bootx.yysc.model.service.SoftService
import com.bootx.yysc.repository.DataBase
import com.bootx.yysc.repository.entity.HistoryEntity
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.util.SharedPreferencesUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class AppDetailViewModel:ViewModel() {

    private var softIconLogService = SoftIconLogService.instance()
    private var softService = SoftService.instance()

    var softDetail by mutableStateOf<SoftDetailEntity>(SoftDetailEntity())
    suspend fun detail(context:Context,token: String,id: String): SoftDetailEntity {
        val historyDao = DataBase.getDb(context)?.getHistoryDao()
        val res = softService.detail(token,id)
        if (res.code == 0) {
            softDetail = res.data
            // 写入历史记录
            CoroutineScope(Dispatchers.IO).launch {
                if (historyDao != null) {
                    val byId = historyDao.getById(res.data.id)
                    if(byId.isEmpty()){
                        historyDao.insert(
                            HistoryEntity(
                            id=res.data.id,
                            name=res.data.name,
                            logo=res.data.logo,
                            packageName = "${res.data.fullName}",
                        )
                        )
                    }else{
                        historyDao.update(
                            HistoryEntity(
                            id=res.data.id,
                            name=res.data.name,
                            logo=res.data.logo,
                            packageName = "${res.data.fullName}",
                            updateDate = Date().time,
                        )
                        )
                    }
                }
            }
        }

        return SoftDetailEntity()
    }

    // 投币
    suspend fun reward(context: Context,softId: String,point: Int,memo: String):Boolean {
        try {
            val res = softIconLogService.reward(SharedPreferencesUtils(context).get("token"),softId,point,memo)
            CommonUtils.toast(context,res.msg)
            if(res.code==0){
                val softDetail1 = softDetail;
                softDetail1.donationIcon = softDetail1.donationIcon+point
                softDetail1.donationMember = softDetail1.donationMember+1
                softDetail = softDetail1
            }
            return res.code==0
        }catch (e:Exception){
            CommonUtils.toast(context,e.toString());
        }
        return false
    }
}