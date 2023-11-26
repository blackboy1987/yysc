package com.bootx.yysc.viewmodel

import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.SignInEntity
import com.bootx.yysc.model.service.SignInService

class UserViewModel:ViewModel() {
    private val signInService = SignInService.instance()

    suspend fun isSign():Boolean{
        return signInService.isSign()
    }

    suspend fun signIn(): SignInEntity {
        return signInService.signIn()
    }
}