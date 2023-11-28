package com.bootx.yysc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bootx.yysc.ui.theme.fontSize10

@Composable
fun Tag(text: String) {
    Text(
        modifier = Modifier.clip(RoundedCornerShape(4.dp)).width(40.dp).height(18.dp).background(Color.Red),
        textAlign= TextAlign.Center,
        fontSize = fontSize10,
        fontFamily = FontFamily.Serif,
        fontStyle = FontStyle.Italic,
        text=text
    )
}