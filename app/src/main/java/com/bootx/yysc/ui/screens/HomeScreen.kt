package com.bootx.yysc.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.LoginBottomSheet

@Composable
fun HomeScreen(navController: NavHostController) {
    Text(text = "home")
    LoginBottomSheet()
}
