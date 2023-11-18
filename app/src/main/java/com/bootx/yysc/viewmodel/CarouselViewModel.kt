package com.bootx.yysc.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.model.entity.CarouselEntity
import com.bootx.yysc.model.service.CarouselService
import com.google.gson.Gson

class CarouselViewModel:ViewModel() {

    private val carouselService = CarouselService.instance()

    var listLoaded by mutableStateOf(false)
        private set

    var carousels = mutableListOf<CarouselEntity>()

    suspend fun fetchList() {
        val res = carouselService.list()
        val gson = Gson()
        if (res.code == 0 && res.data != null) {
            val tmpList = mutableListOf<CarouselEntity>()
            tmpList.addAll(res.data)
            carousels = tmpList
            listLoaded = true
        } else {
            Log.e("fetchList",gson.toJson(res))
        }
    }
}