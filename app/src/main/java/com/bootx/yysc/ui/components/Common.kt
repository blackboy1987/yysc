package com.bootx.yysc.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14

@Composable
fun RightIcon(onClick:()->Unit) {
    Icon(
        modifier = Modifier.clickable {
            onClick()
        }.size(16.dp),
        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
        contentDescription = "",
    )
}

@Composable
fun LeftIcon(onClick:()->Unit) {
    Icon(
        modifier = Modifier.clickable {
            onClick()
        }.size(16.dp),
        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
        contentDescription = "",
    )
}

@Composable
fun DownloadButton(onClick:()->Unit) {
    Button(
        modifier = Modifier.padding(0.dp).height(36.dp).width(80.dp),
        onClick = onClick,
    ) {
        Text(
            text = "下载",
            fontSize = fontSize12,
        )
    }
}

@Composable
fun CardTitle(text: String) {
    Text(text = text, fontSize = fontSize14)
}

@Composable
fun TopBarTitle(text: String) {
    Text(text = text, fontSize = MaterialTheme.typography.titleMedium.fontSize)
}