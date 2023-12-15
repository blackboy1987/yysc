package com.bootx.yysc.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.model.entity.AppInfo
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.RightIcon
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.util.AppInfoUtils
import com.bootx.yysc.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController, homeViewModel: HomeViewModel = viewModel()) {

    val context = LocalContext.current

    var appInfo by remember {
        mutableStateOf(AppInfo())
    }

    LaunchedEffect(Unit) {
        appInfo = AppInfoUtils.getAppInfo(context, "com.bootx.yysc")
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { }, navigationIcon = {
                LeftIcon {
                    navController.popBackStack()
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        AsyncImage(model = appInfo.appIcon, contentDescription = "")
                        Text(text = appInfo.appName)
                        Text(text = "${appInfo.versionName}(${appInfo.versionCode})")
                    }
                }
                item {
                    HorizontalDivider()
                    ListItem(
                        modifier = Modifier.clickable {
                            navController.navigate(Destinations.OtherFrame.route)
                        },
                        trailingContent = {
                            RightIcon {

                            }
                        },
                        headlineContent = { Text(text = "全站规则") }
                    )
                    HorizontalDivider()
                }
                item {
                    ListItem(
                        trailingContent = {
                            RightIcon {

                            }
                        },
                        headlineContent = { Text(text = "设计理念") }
                    )
                    HorizontalDivider()
                }
                item {
                    ListItem(
                        modifier = Modifier.clickable {
                            navController.navigate(Destinations.QunZuFrame.route)
                        },
                        trailingContent = {
                            RightIcon {

                            }
                        },
                        headlineContent = { Text(text = "交流群组") }
                    )
                    HorizontalDivider()
                }
                item {
                    ListItem(
                        trailingContent = {
                            RightIcon {

                            }
                        },
                        headlineContent = { Text(text = "联系作责") }
                    )
                    HorizontalDivider()
                }
                item {
                    ListItem(
                        trailingContent = {
                            RightIcon {

                            }
                        },
                        headlineContent = { Text(text = "酷盾安全") }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}