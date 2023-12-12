package com.bootx.yysc.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.SignInEntity
import com.bootx.yysc.model.service.SignInService
import com.bootx.yysc.model.service.UserService
import com.bootx.yysc.repository.entity.UserEntity

class UserViewModel:ViewModel() {
    private val signInService = SignInService.instance()
    private val userService = UserService.instance()

    var signInInfo by mutableStateOf(SignInEntity())
    var userInfo by mutableStateOf(UserEntity(

    ))

    suspend fun isSign(token: String,):Boolean{
        return signInService.isSign(token)
    }

    suspend fun signIn(token: String,) {
        val res = signInService.signIn(token);
        if(res.code==0){
            signInInfo = res.data
        }
    }

    suspend fun loadUserInfo(token: String) {
        try {
            val res = userService.currentUser(token)
            if (res.code == 0) {
                userInfo = res.data
                Log.e("loadUserInfo", "loadUserInfo: ${userInfo.toString()}", )
            }
        } catch (_: Throwable) {
        }
    }
}