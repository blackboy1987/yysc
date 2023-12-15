package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun PlazaScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxHeight(),
    ) {
        Text(text = "PlazaScreen")
    }
}
