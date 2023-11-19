package com.bootx.yysc.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
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

@Composable
fun MyCard(
    title: String,
    onClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(padding8)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .padding(padding8)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = title, fontSize = fontSize12)
            Icon(
                modifier=Modifier.clickable {
                    onClick()
                },
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "",
            )
        }
        Spacer(modifier = Modifier.height(height16))
        Column(content = content)
    }
}
