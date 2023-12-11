package com.bootx.yysc.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Demo() {
    Icon(
        modifier = Modifier.clickable {

        },
        imageVector = Icons.Default.Notifications, contentDescription = "")
}

@Preview(
    showBackground = true
)
@Composable
fun PreviewDemo() {
    Demo()
}