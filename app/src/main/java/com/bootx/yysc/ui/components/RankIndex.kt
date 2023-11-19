package com.bootx.yysc.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun RankIndex(index: Int) {
    var color = Color(0xFFc74b25)
    if(index==1) {
        color = Color(0xFFc74b25)
    }else if(index==2) {
        color = Color(0xFFfccc85)
    }else if(index==3) {
        color = Color(0xFF4b9dde)
    }else {
        color = Color(0xFF020202)
    }
    Text(
        text = "$index",
        modifier = Modifier.width(60.dp),
        textAlign = TextAlign.Center,
        color = color,
    )
}