package com.bootx.yysc.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotScreen(navController: NavHostController) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.clickable {
                        expanded=!expanded
                    },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = "今日应用榜")
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                }
            },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = ""
                )
            })
    }) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            DropdownMenu(
                modifier = Modifier
                    .width(140.dp)
                    .align(Alignment.CenterHorizontally),
                offset = DpOffset(30.dp, (-50).dp),
                expanded = expanded, onDismissRequest = { /*TODO*/ }) {
                DropdownMenuItem(text = { Text(text = "今日应用榜") }, onClick = {
                    expanded = false
                })
                DropdownMenuItem(text = { Text(text = "应用总榜") }, onClick = {
                    expanded = false
                })
                DropdownMenuItem(text = { Text(text = "封神榜") }, onClick = {
                    expanded = false
                })
                DropdownMenuItem(text = { Text(text = "达人榜") }, onClick = {
                    expanded = false
                })

            }
        }
    }
}
