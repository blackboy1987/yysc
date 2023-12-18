package com.bootx.yysc.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.MemberEntity
import com.bootx.yysc.model.service.MemberService
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.util.SharedPreferencesUtils

class MemberViewModel:ViewModel() {
    private val memberService = MemberService.instance()

    var memberInfo by mutableStateOf(MemberEntity(

    ))






    suspend fun loadUserInfo(context: Context, id: String) {
        try {
            val res = memberService.load(SharedPreferencesUtils(context).get("token"),id)
            if (res.code == 0) {
                memberInfo = res.data
            }
        } catch (e: Throwable) {
            CommonUtils.toast(context,e.message.toString())
        }
    }
}