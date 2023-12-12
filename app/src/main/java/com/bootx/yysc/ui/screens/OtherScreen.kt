package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.MyWebView
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.components.rememberWebViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherScreen(navController: NavHostController) {
    val state = rememberWebViewState(url = "http://www.baidu.com")
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TopBarTitle("游戏类别") },
            )
            LeftIcon {
                navController.popBackStack()
            }
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            MyWebView(state = state)
        }
    }
}