package com.bootx.yysc.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.SoftIcon4
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SettingScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel()) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val token = SharedPreferencesUtils(context).get("token")
    LaunchedEffect(Unit) {
        userViewModel.loadUserInfo(token)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { TopBarTitle(text = "设置") }, navigationIcon = {
                LeftIcon {
                    navController.popBackStack()
                }
            })
        },
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn() {
                if (userViewModel.userInfo.id > 0) {
                    item {
                        ListItem(
                            headlineContent = {
                                Text(
                                    text = "用户头像",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        )
                        ListItem(
                            trailingContent = {
                                SoftIcon4(url = "${userViewModel.userInfo.avatar}")
                            },
                            headlineContent = { Text(text = "头像") }
                        )
                        ListItem(
                            modifier = Modifier.clickable {
                                coroutineScope.launch {
                                    sheetState.show()
                                }
                            },
                            trailingContent = {
                                Text(text = "${userViewModel.userInfo.username}")
                            },
                            headlineContent = { Text(text = "昵称") }
                        )
                        ListItem(
                            trailingContent = {
                                Text(text = "已授权")
                            },
                            headlineContent = { Text(text = "蓝奏云") }
                        )
                        ListItem(
                            headlineContent = {
                                HorizontalDivider()
                            }
                        )

                    }
                } else {
                    item {
                        ListItem(headlineContent = {
                            Text(text = "立即登录")
                        })
                    }
                }
                item {
                    ListItem(headlineContent = {
                        Text(text = "界面显示", fontWeight = FontWeight.Bold)
                    })
                    ListItem(
                        trailingContent = {
                            Text(text = "自行切换")
                        },
                        headlineContent = { Text(text = "夜间模式") }
                    )
                    ListItem(
                        trailingContent = {
                            Text(text = "白天")
                        },
                        headlineContent = { Text(text = "暗黑界面") }
                    )
                    ListItem(
                        trailingContent = {
                            Text(text = "显示")
                        },
                        headlineContent = { Text(text = "消息通知") }
                    )
                    ListItem(
                        trailingContent = {
                            Text(text = "隐藏")
                        },
                        headlineContent = { Text(text = "评分提醒") }
                    )
                    ListItem(
                        trailingContent = {
                            Text(text = "开启")
                        },
                        headlineContent = { Text(text = "后台下载") }
                    )
                    ListItem(
                        headlineContent = {
                            HorizontalDivider()
                        }
                    )
                }
                item {
                    ListItem(
                        headlineContent = { Text(text = "软件相关", fontWeight = FontWeight.Bold) }
                    )
                    ListItem(
                        trailingContent = {
                            Text(text = "关闭")
                        },
                        headlineContent = { Text(text = "始终外置下载") }
                    )
                    ListItem(
                        headlineContent = { Text(text = "分享爱尚应用") }
                    )
                    ListItem(
                        trailingContent = {
                            Text(text = "77.47MB")
                        },
                        headlineContent = { Text(text = "清理缓存") }
                    )
                    ListItem(
                        headlineContent = { Text(text = "奔溃日志") }
                    )
                    ListItem(
                        headlineContent = { Text(text = "关于应用") }
                    )
                }
                if (userViewModel.userInfo.id > 0) {
                    item {
                        TextButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { /*TODO*/ }
                        ) {
                            Text(text = "退出登录")
                        }
                    }
                }
            }
        }
    }
    ModalBottomSheetLayout(
        sheetGesturesEnabled = false,
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        sheetState = sheetState,
        sheetContent = {
            var username by remember {
                mutableStateOf("")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "修改昵称",
                    modifier = Modifier.padding(top = 24.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "请输入心仪的昵称",
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                )
                OutlinedTextField(value = username, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp), onValueChange = {
                    username = it
                })
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier.weight(1.0f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        OutlinedButton(onClick = {
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                        }, modifier = Modifier.fillMaxWidth(0.8f)) {
                            Text(text = "取消")
                        }
                    }
                    Box(modifier = Modifier.weight(1.0f), contentAlignment = Alignment.CenterEnd) {
                        Button(enabled = username.isNotEmpty(),onClick = {
                            coroutineScope.launch {
                                userViewModel.update(context,token,username)
                                userViewModel.loadUserInfo(token)
                                sheetState.hide()
                            }
                        }, modifier = Modifier.fillMaxWidth(0.8f)) {
                            Text(text = "修改")
                        }
                    }
                }
            }
        }
    ) {

    }
}