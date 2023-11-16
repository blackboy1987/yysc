package com.bootx.yysc.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.height16
import com.bootx.yysc.ui.theme.padding8
import com.bootx.yysc.ui.theme.primaryFontColor

@Composable
fun MyCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .padding(padding8)
            .fillMaxWidth(),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.White,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.White,
        )
    ) {
        Row(
            modifier = Modifier
                .padding(padding8)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = title, fontSize = fontSize12, color = primaryFontColor)
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "",
                tint = Color(0xFFb7b7b7)
            )
        }
        Spacer(modifier = Modifier.height(height16))
        Column(content = content)
    }
}
