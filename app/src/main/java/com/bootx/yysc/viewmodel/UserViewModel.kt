package com.bootx.yysc.viewmodel

import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.SignInEntity
import com.bootx.yysc.model.service.SignInService

class UserViewModel:ViewModel() {
    private val signInService = SignInService.instance()

    suspend fun isSign(token: String,):Boolean{
        return signInService.isSign(token)
    }

    suspend fun signIn(token: String,): SignInEntity {
        return signInService.signIn(token)
    }
}