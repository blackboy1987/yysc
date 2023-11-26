package com.bootx.yysc.ui.components

import androidx.compose.runtime.Composable

@Composable
fun Item(type: Int,) {
    when(type){
        1-> Item1(type);
        2-> Item2(type);
        3-> Item3(type);
        4-> Item4(type);
    }
}