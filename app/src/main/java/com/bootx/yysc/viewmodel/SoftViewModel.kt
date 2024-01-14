package com.bootx.yysc.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.DownloadEntityResponse
import com.bootx.yysc.model.entity.SoftDetailEntity
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.model.service.SoftService
import com.bootx.yysc.repository.DataBase
import com.bootx.yysc.repository.entity.HistoryEntity
import com.bootx.yysc.util.SharedPreferencesUtils
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class SoftViewModel:ViewModel() {
    private val softService = SoftService.instance()

    var listLoaded by mutableStateOf(false)
        private set
    var listLoadedErrorData by mutableStateOf("")
        private set

    var softListLoaded by mutableStateOf(false)
        private set

    var softList by mutableStateOf(listOf<SoftEntity>())


    var softDetail by mutableStateOf<SoftDetailEntity>(SoftDetailEntity())

    var pageNumber by mutableIntStateOf(1)
        private set


    var hasMore by mutableStateOf(true)

    var loading by mutableStateOf(false)
    var pageSize by mutableStateOf(20)
        private set


    suspend fun orderBy(context: Context,pageNumber: Int,pageSize: Int,orderBy: String): List<SoftEntity> {
        try {
            val res = softService.orderBy(SharedPreferencesUtils(context).get("token"),pageNumber, pageSize,orderBy)
            if (res.code == 0 && res.data != null) {
                val tmpList = mutableListOf<SoftEntity>()
                if (pageNumber != 1) {
                    tmpList.addAll(softList)
                }
                tmpList.addAll(res.data)
                softList = tmpList
                if (res.data.size == pageSize) {
                    hasMore = true
                    this.pageNumber += 1
                } else {
                    hasMore = false
                }
            }
        }catch (e: Throwable){
            return listOf()
        }
        return listOf()
    }

    suspend fun loadMore(token: String,orderBy: String) {
        softListLoaded = false
        val res = softService.orderBy(token,pageNumber,20,orderBy)
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
                        historyDao.insert(HistoryEntity(
                            id=res.data.id,
                            name=res.data.name,
                            logo=res.data.logo,
                            packageName = "${res.data.fullName}",
                        ))
                    }else{
                        historyDao.update(HistoryEntity(
                            id=res.data.id,
                            name=res.data.name,
                            logo=res.data.logo,
                            packageName = "${res.data.fullName}",
                            updateDate = Date().time,
                        ))
                    }
                }
            }
        }

        return SoftDetailEntity()
    }

    suspend fun download(token: String,id: Int): DownloadEntityResponse {
        return softService.download(token,id)
    }

    suspend fun more(context: Context, id: String) {
        val res = softService.more(SharedPreferencesUtils(context).get("token"),id)
        if (res.code == 0) {
            softDetail = res.data
        }
    }
}