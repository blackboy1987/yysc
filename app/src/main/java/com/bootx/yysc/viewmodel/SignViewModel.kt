package com.bootx.yysc.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.SignInEntity
import com.bootx.yysc.model.entity.SignRank
import com.bootx.yysc.model.service.SignInService
import com.bootx.yysc.model.service.UserService
import com.bootx.yysc.repository.entity.UserEntity
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.util.SharedPreferencesUtils

class SignViewModel:ViewModel() {
    private val signInService = SignInService.instance()
    private val userService = UserService.instance()

    var signInInfo by mutableStateOf(SignInEntity())
    var userInfo by mutableStateOf(UserEntity(

    ))

    var list by mutableStateOf(listOf<SignRank>())
    var pageNumber by mutableIntStateOf(1)
        private set
    var hasMore by mutableStateOf(true)
    var loading by mutableStateOf(false)
    var pageSize by mutableIntStateOf(20)
        private set

    suspend fun isSign(context: Context):Boolean{
        return signInService.isSign(SharedPreferencesUtils(context).get("token"))
    }

    suspend fun signIn(token: String,) {
        val res = signInService.signIn(token);
        if(res.code==0){
            signInInfo = res.data
        }
    }

    suspend fun list(context: Context) {
        try {
            val res = signInService.list(SharedPreferencesUtils(context).get("token"),pageNumber,pageSize)
            if (res.code == 0) {
                val tmpList = mutableListOf<SignRank>()
                if (pageNumber != 1) {
                    tmpList.addAll(list)
                }
                tmpList.addAll(res.data)
                list = tmpList
                if (res.data.size == pageSize) {
                    hasMore = true
                    this.pageNumber += 1
                } else {
                    hasMore = false
                }
            }else{
                CommonUtils.toast(context,res.msg)
            }
        }catch (e: Throwable){
            Log.e("search", "search: ${e.toString()}", )
            CommonUtils.toast(context, e.toString())
        }
    }

    /**
     * 直接从数据库里面获取
     */
    suspend fun loadUserInfo(context:Context) {
        try {
            val res = userService.currentUser(SharedPreferencesUtils(context).get("token"))
            if (res.code == 0) {
                userInfo = res.data
                SharedPreferencesUtils(context).set("userId","${userInfo.id}")
            }
        } catch (_: Throwable) {
        }
    }

    suspend fun update(context:Context,token: String, username: String) {
        try {
            val res = userService.update(token,username)
            CommonUtils.toast(context,res.msg)
        } catch (_: Throwable) {
        }
    }
}