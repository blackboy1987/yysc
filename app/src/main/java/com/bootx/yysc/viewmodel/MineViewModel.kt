package com.bootx.yysc.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bootx.yysc.repository.DataBase
import com.bootx.yysc.repository.entity.HistoryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MineViewModel:ViewModel() {

    var list by mutableStateOf(listOf<HistoryEntity>())

    suspend fun load(context: Context) {
        val historyDao = DataBase.getDb(context)?.getHistoryDao()
        CoroutineScope(Dispatchers.IO).launch {
            list = historyDao?.getAll()!!
        }
    }

    suspend fun clearHistory(context: Context) {
        val historyDao = DataBase.getDb(context)?.getHistoryDao()
        CoroutineScope(Dispatchers.IO).launch {
            historyDao?.delete()!!
            list = historyDao.getAll()
        }
    }
}