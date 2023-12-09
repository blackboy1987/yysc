package com.bootx.yysc.viewmodel

import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.SignInEntity
import com.bootx.yysc.model.service.SignInService
import com.bootx.yysc.model.service.UserService
import com.bootx.yysc.repository.entity.UserEntity

class UserViewModel:ViewModel() {
    private val signInService = SignInService.instance()
    private val userService = UserService.instance()


    suspend fun isSign(token: String,):Boolean{
        return signInService.isSign(token)
    }

    suspend fun signIn(token: String,): SignInEntity {
        return signInService.signIn(token)
    }

    suspend fun loadUserInfo(token: String): UserEntity {
        try {
            val res = userService.currentUser(token)
            if (res.code == 0) {
                return res.data
            }
        } catch (_: Throwable) {
        }
        return UserEntity(
            id=0,
            avatar="",
            username = "",
        )
    }
}