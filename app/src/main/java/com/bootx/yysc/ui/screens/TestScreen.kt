package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.touGao.TouGaoModalBottomSheet

@Composable
fun TestScreen(
    navController: NavHostController,
) {
    var isOpen by remember {
        mutableStateOf(true)
    }
    Scaffold(

    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column {
                Button(onClick = {
                    isOpen = true
                }) {
                    Text(text = "open")
                }
            }
        }
    }
    if(isOpen){
        TouGaoModalBottomSheet(onClose = {
            isOpen = false
        }){
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp,end = 8.dp, top=16.dp, bottom = 8.dp),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "添加安装包",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                )
                var text by remember { mutableStateOf("") }
                Text(text = "分享地址")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(), value = text, onValueChange = { text = it })
                Text(text = "密码")
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(), value = text, onValueChange = { text = it })
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        isOpen = false
                    }
                ) {
                    Text("保存")
                }
            }
        }
    }
}