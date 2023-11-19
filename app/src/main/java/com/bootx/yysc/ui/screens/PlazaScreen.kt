package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.components.SoftItem

@Composable
fun PlazaScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxHeight(),
    ) {
        LazyColumn(){
            items(100) {
                SoftItem(SoftEntity(
                    id=1,
                    downloadUrl = "abc",
                    logo = "https://img.shouji.com.cn/simg/20190301/2019030143312439.png",
                    name = "PUBGTool官方版",
                    images = "",
                    memo="",
                    score = 1.1,
                    size = "12.34M",
                    updateDate = "2023-11-16 23:52:33"
                ))
            }
        }
    }
}
