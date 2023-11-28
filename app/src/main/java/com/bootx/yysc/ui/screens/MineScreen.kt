package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArtTrack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bootx.yysc.extension.onScroll
import com.bootx.yysc.ui.components.CardTitle
import com.bootx.yysc.ui.components.Item0
import com.bootx.yysc.ui.components.RightIcon
import com.bootx.yysc.ui.components.SoftIcon
import com.bootx.yysc.ui.components.SoftIcon4
import com.bootx.yysc.ui.components.SoftIcon8
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.components.item0List
import com.bootx.yysc.ui.theme.fontSize10
import com.bootx.yysc.ui.theme.fontSize14

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MineScreen(navController: NavHostController) {
    var showTopBar by remember {
        mutableStateOf(false)
    }
    val lazyListState = rememberLazyListState()
    lazyListState.onScroll(callback = { index ->
        showTopBar = index > 0
    })

    Surface {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        if (showTopBar) TopBarTitle(text = "卡死hi显示标题了")
                    },
                    navigationIcon = {
                        if (showTopBar) SoftIcon4("https://bootx-tuchuang.oss-cn-hangzhou.aliyuncs.com/avatar/1.png")
                    },
                    actions = {
                        Icon(imageVector = Icons.Filled.Notifications, contentDescription = "")
                        Icon(imageVector = Icons.Filled.ArtTrack, contentDescription = "")
                    }
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.padding(it),
                state = lazyListState,
            ) {
                item {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        SoftIcon8("https://bootx-tuchuang.oss-cn-hangzhou.aliyuncs.com/avatar/1.png")
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp, end = 32.dp),
                        ) {
                            Text(text = "请先登录")
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(text = "lv.0")
                                Text(text = "10/2580")
                            }
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                LinearProgressIndicator(
                                    progress = {0.3f},
                                    strokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,
                                )
                            }
                        }
                        RightIcon {

                        }
                    }
                }
                item {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.weight(1.0f),
                            text = "我的账户",
                            fontWeight = FontWeight.Bold,
                            fontSize = fontSize14
                        )
                        Text(text = "免费广告权益", fontSize = fontSize10)
                        Icon(
                            tint = Color(0xFFAEAEAE),
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = ""
                        )
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .height(60.dp),
                    ) {
                        Item(title = "15", title2 = "硬币", modifier = Modifier.weight(1.0f))
                        MyDivider1()
                        Item(title = "15", title2 = "关注", modifier = Modifier.weight(1.0f))
                        MyDivider1()
                        Item(title = "15", title2 = "粉丝", modifier = Modifier.weight(1.0f))
                        MyDivider1()
                        Item(title = "15", title2 = "付费", modifier = Modifier.weight(1.0f))
                    }
                }

                item {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalArrangement = Arrangement.Center,
                        maxItemsInEachRow = 4,
                    ) {
                        item0List.forEach { item ->
                            Item0(item)
                        }
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        CardTitle(text = "浏览历史")
                        RightIcon {
                            navController.navigate("ListFrame/好评如潮/01")
                        }
                    }
                    HistoryItem()
                }
            }

        }
    }
}

@Composable
fun Item(title: String, title2: String, modifier: Modifier) {
    Column(
        modifier = Modifier.then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title, fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = title2, fontSize = MaterialTheme.typography.titleSmall.fontSize,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun MyDivider1() {
    HorizontalDivider(
        color = Color(0xFFAEAEAE), modifier = Modifier
            .fillMaxHeight()
            .width(1.dp)
    )
}

@Composable
fun HistoryItem() {
    repeat(100) {
        ListItem(
            modifier = Modifier.clickable {

            },
            colors = ListItemDefaults.colors(
                containerColor = Color.White
            ),
            headlineContent = {
                Text(text = "KOOK")
            },
            supportingContent = {
                Text(text = "com.bootx.yysc")
            },
            leadingContent = {
                SoftIcon("https://lsw-fast.lenovo.com.cn/appstore/normal/apps/4142-2022-06-30025833-1656572313892.png")
            },
            trailingContent = {
                Text(text = "10天前")
            },
            tonalElevation = 5.dp,
            shadowElevation = 10.dp,
        )
    }
}
