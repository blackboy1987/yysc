package com.bootx.yysc.ui.screens

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.viewmodel.LoginViewModel


@Composable
fun LoginScreen(navController: NavHostController,loginViewModel: LoginViewModel= viewModel()) {
    var value by remember() {
        mutableStateOf("")
    }
    TextField(value = value, onValueChange = {
        value = it
    })
}

