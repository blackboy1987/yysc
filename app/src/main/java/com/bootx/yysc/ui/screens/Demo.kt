package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.bootx.yysc.util.StoreManager
import kotlinx.coroutines.launch

@Composable
fun Demo(navController: NavHostController) {
    val storeManager: StoreManager = StoreManager(LocalContext.current)
    val coroutineScope = rememberCoroutineScope()
    Column {
        var str by remember {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = str,
            onValueChange = {str=it}
        )
        Button(onClick = {
            coroutineScope.launch {0
                storeManager.save("aaa",str);
            }
        }) {
            Text(text="保存字符串")
        }
        val strData = storeManager.get("aaa").collectAsState(initial = "");


        Text(text = "当前存储的数据：${strData.value}")
    }



}