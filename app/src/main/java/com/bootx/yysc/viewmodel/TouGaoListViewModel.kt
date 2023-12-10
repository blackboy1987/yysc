package com.bootx.yysc.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.AppInfo
import com.bootx.yysc.model.service.TouGaoEntity
import com.bootx.yysc.model.service.TouGaoInfo
import com.bootx.yysc.model.service.TouGaoService
import com.bootx.yysc.util.AppInfoUtils


class TouGaoListViewModel : ViewModel() {
    private val touGaoService = TouGaoService.instance()

    /**
     * 列表加载状态
     */
    var listLoaded by mutableStateOf(false)
        private set

    /**
     * 列表加载失败的错误信息
     */
    var listLoadedErrorData by mutableStateOf("")
        private set

    /**
     * 列表加载完的数据
     */

    var list = mutableListOf<TouGaoEntity>()
    /**
     * 列表加载完的数据
     */

    var touGaoInfo by mutableStateOf(
        TouGaoInfo(

        )
    )

    /**
     * 页码
     */
    var pageNumber by mutableStateOf(1)
        private set


    @SuppressLint("QueryPermissionsNeeded")
    fun getAppList(context: Context): List<AppInfo> {
        return AppInfoUtils.getAppList(context)
    }

    suspend fun list(token: String,pageNumber: Int,pageSize: Int,type: Int){
        try {
            listLoaded = true
            val res = touGaoService.list(token,pageNumber, pageSize,type)
            if (res.code == 0 && res.data != null) {
                val tmpList = mutableListOf<TouGaoEntity>()
                if (pageNumber != 1) {
                    tmpList.addAll(list)
                }
                tmpList.addAll(res.data)
                list = tmpList
                listLoaded = false
                this.pageNumber += 1
            }
        }catch (e: Throwable){
            listLoaded = false
            Log.e("touGaoService", "list: ${e.message}", )
        }
    }

    suspend fun loadInfo(token: String) {
        try {
            val res = touGaoService.loadInfo(token)
            if (res.code == 0) {
                touGaoInfo = res.data
            }
        }catch (e: Throwable){
            Log.e("touGaoService", "list: ${e.message}", )
        }
    }

    suspend fun update(token: String, touGaoInfoId: Int, type: Int) {
        try {
            val res = touGaoService.update(token,touGaoInfoId,type)

        }catch (e: Throwable){
        }
    }


}