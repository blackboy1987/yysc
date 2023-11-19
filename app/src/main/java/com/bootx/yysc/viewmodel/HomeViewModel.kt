package com.bootx.yysc.viewmodel

import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.model.service.SoftService
import com.google.gson.Gson

class HomeViewModel:ViewModel() {
    private val softService = SoftService.instance()

}