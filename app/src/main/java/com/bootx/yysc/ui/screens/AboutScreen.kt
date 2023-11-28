package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.RightIcon
import com.bootx.yysc.ui.components.SoftIcon12
import com.bootx.yysc.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController, homeViewModel: HomeViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { }, navigationIcon = {
                LeftIcon {

                }
            })
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

            LazyColumn(
                modifier = Modifier.padding(32.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        SoftIcon12(url = "https://bootx-tuchuang.oss-cn-hangzhou.aliyuncs.com/avatar/198.png")
                        Text(text = "爱尚应用")
                        Text(text = "1.0.1(101)")
                    }
                }
                item {
                    Divider()
                    ListItem(
                        trailingContent = {
                            RightIcon {

                            }
                        },
                        headlineContent = { Text(text = "全站规则") }
                    )
                    Divider()
                }
                item {
                    ListItem(
                        trailingContent = {
                            RightIcon {

                            }
                        },
                        headlineContent = { Text(text = "设计理念") }
                    )
                    Divider()
                }
                item {
                    ListItem(
                        trailingContent = {
                            RightIcon {

                            }
                        },
                        headlineContent = { Text(text = "交流群组") }
                    )
                    Divider()
                }
                item {
                    ListItem(
                        trailingContent = {
                            RightIcon {

                            }
                        },
                        headlineContent = { Text(text = "联系作责") }
                    )
                    Divider()
                }
                item {
                    ListItem(
                        trailingContent = {
                            RightIcon {

                            }
                        },
                        headlineContent = { Text(text = "酷盾安全") }
                    )
                    Divider()
                }
            }
        }
    }
}