package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.SoftIcon4
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavHostController,homeViewModel: HomeViewModel= viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(title = { TopBarTitle(text = "设置") }, navigationIcon = {
                LeftIcon {
                    
                }
            })
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

            LazyColumn(){
                item{
                    Text(text = "用户头像")
                }
                item{
                    ListItem(
                        trailingContent = {
                            SoftIcon4(url = "https://bootx-tuchuang.oss-cn-hangzhou.aliyuncs.com/avatar/120.png")
                        },
                        headlineContent = { Text(text = "头像")}
                    )
                }
                item{
                    ListItem(
                        trailingContent = {
                            Text(text = "blackboy")
                        },
                        headlineContent = { Text(text = "昵称")}
                    )
                }
                item{
                    ListItem(
                        trailingContent = {
                            Text(text = "已授权")
                        },
                        headlineContent = { Text(text = "蓝奏云")}
                    )
                }
                item{
                    Divider()
                }
                item{
                    Text(text = "界面显示")
                }

                item{
                    ListItem(
                        trailingContent = {
                            Text(text = "自行切换")
                        },
                        headlineContent = { Text(text = "夜间模式")}
                    )
                }
                item{
                    ListItem(
                        trailingContent = {
                            Text(text = "白天")
                        },
                        headlineContent = { Text(text = "暗黑界面")}
                    )
                }
                item{
                    ListItem(
                        trailingContent = {
                            Text(text = "显示")
                        },
                        headlineContent = { Text(text = "消息通知")}
                    )
                }
                item{
                    ListItem(
                        trailingContent = {
                            Text(text = "隐藏")
                        },
                        headlineContent = { Text(text = "评分提醒")}
                    )
                }
                item{
                    ListItem(
                        trailingContent = {
                            Text(text = "开启")
                        },
                        headlineContent = { Text(text = "后台下载")}
                    )
                }
                item{
                    Divider()
                }
                item{
                    Text(text = "软件相关")
                }
                item{
                    ListItem(
                        trailingContent = {
                            Text(text = "关闭")
                        },
                        headlineContent = { Text(text = "始终外置下载")}
                    )
                }
                item{
                    ListItem(
                        headlineContent = { Text(text = "分享爱尚应用")}
                    )
                }
                item{
                    ListItem(
                        trailingContent = {
                            Text(text = "77.47MB")
                        },
                        headlineContent = { Text(text = "清理缓存")}
                    )
                }
                item{
                    ListItem(
                        headlineContent = { Text(text = "奔溃日志")}
                    )
                }
                item{
                    ListItem(
                        headlineContent = { Text(text = "关于应用")}
                    )
                }
                item{
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