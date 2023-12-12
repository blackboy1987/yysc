package com.bootx.yysc.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.LoginEntity
import com.bootx.yysc.model.service.LoginService
import com.bootx.yysc.model.service.UserService
import com.bootx.yysc.repository.DataBase
import com.bootx.yysc.repository.entity.UserEntity
import com.bootx.yysc.util.SharedPreferencesUtils

class LoginViewModel : ViewModel() {
    private val loginService = LoginService.instance()
    private val userService = UserService.instance()
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


    suspend fun login(context: Context, username: String, password: String){
        try {
            loading = true
            val res = loginService.login(username, password)
            if (res.code == 0) {
                data = res.data
                val currentUser = userService.currentUser(data.token)
                // 写入到数据库
                DataBase.getDb(context)?.getUserDao()?.insert(currentUser.data)
                SharedPreferencesUtils(context).set("userId","${currentUser.data.id}")
            }
            loading = false
        } catch (e: Throwable) {
            loading = false
        }
    }
}