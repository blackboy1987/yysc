package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRankScreen(
    navController: NavHostController,
    vm: AppViewModel = viewModel(),
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "应用总榜",
                            fontSize = MaterialTheme.typography.titleMedium.fontSize
                        )
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                    }
                    DropdownMenu(
                        expanded = true,
                        onDismissRequest = { /*TODO*/ },
                        offset = DpOffset(0.dp, (-56).dp)
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "今日应用榜") },
                            onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text(text = "应用总榜") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text(text = "封神榜") }, onClick = { /*TODO*/ })
                        DropdownMenuItem(text = { Text(text = "达人榜") }, onClick = { /*TODO*/ })
                    }
                },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                },

                )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {

        }
    }
}