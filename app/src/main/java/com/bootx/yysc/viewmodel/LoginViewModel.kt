package com.bootx.yysc.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.LoginEntity
import com.bootx.yysc.model.service.LoginService

class LoginViewModel : ViewModel() {
    private val loginService = LoginService.instance()


    // 加载状态
    var loading by mutableStateOf(false)
        private set

    // 列表数据
    var data by mutableStateOf<LoginEntity>(
        LoginEntity(
            id = 0,
            username = "",
            avatar = "",
            token = "",
        )
    )


    suspend fun login(username: String, password: String){
        try {
            loading = true
            val res = loginService.login(username, password)
            if (res.code == 0) {
                data = res.data
            }
            Log.e("login", "login success: ${data.toString()}", )
            loading = false
        } catch (e: Throwable) {
            Log.e("login", "login error: ${e.toString()}", )
            loading = false
        }
    }
}