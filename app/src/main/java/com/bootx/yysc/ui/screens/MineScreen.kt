package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.extension.onScroll
import com.bootx.yysc.model.service.SettingEntity
import com.bootx.yysc.repository.entity.HistoryEntity
import com.bootx.yysc.ui.components.RightIcon
import com.bootx.yysc.ui.components.SoftIcon4
import com.bootx.yysc.ui.components.SoftIcon6
import com.bootx.yysc.ui.components.SoftIcon6_8
import com.bootx.yysc.ui.components.SoftIcon8
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.theme.fontSize10
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.util.CommonUtils
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.util.StoreManager
import com.bootx.yysc.viewmodel.MineViewModel
import com.bootx.yysc.viewmodel.UserViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MineScreen(
    navController: NavHostController,
    mineViewModel: MineViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel()
) {
    val gson = Gson()
    val storeManager: StoreManager = StoreManager(LocalContext.current)
    var showTopBar by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()


    val lazyListState = rememberLazyListState()
    lazyListState.onScroll(callback = { index ->
        showTopBar = index > 0
    })

    val setting = remember {
        mutableStateOf(SettingEntity())
    }

    LaunchedEffect(Unit) {
        mineViewModel.load(context, SharedPreferencesUtils(context).get("token"))
        // 获取用户信息
        userViewModel.loadUserInfo(SharedPreferencesUtils(context).get("token"))
    }

    Surface {
        setting.value = gson.fromJson(
            storeManager.get("setting")
                .collectAsState(initial = gson.toJson(SettingEntity())).value,
            SettingEntity::class.java
        )
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        if (showTopBar) TopBarTitle(text = "${userViewModel.userInfo.username}")
                    },
                    navigationIcon = {
                        if (showTopBar) SoftIcon4("${userViewModel.userInfo.avatar}")
                    },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(Destinations.NotifyFrame.route)
                        }) {
                            Icon(imageVector = Icons.Filled.Notifications, contentDescription = "")
                        }
                        IconButton(onClick = {
                            navController.navigate(Destinations.SettingFrame.route)
                        }) {
                            Icon(imageVector = Icons.Filled.Settings, contentDescription = "")
                        }
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
                        SoftIcon8("${userViewModel.userInfo.avatar}")
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp, end = 32.dp),
                        ) {
                            Text(
                                modifier = Modifier.clickable {
                                    if (userViewModel.userInfo.id == 0) {
                                        navController.navigate(Destinations.LoginFrame.route)
                                    }
                                },
                                text = "${userViewModel.userInfo.username}"
                            )
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(text = "lv.0")
                                Text(text = "${userViewModel.userInfo.point}/${userViewModel.userInfo.nextPoint}")
                            }
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                LinearProgressIndicator(
                                    progress = { (userViewModel.userInfo.point + 0.0f) / userViewModel.userInfo.nextPoint },
                                    strokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,
                                )
                            }
                        }
                        RightIcon {
                            coroutineScope.launch {
                                navController.navigate(
                                    Destinations.MemberFrame.route + "/${
                                        userViewModel.userInfo.id
                                    }"
                                )
                            }
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
                        Item(title = "${userViewModel.userInfo.point ?: 0}",
                            title2 = "硬币",
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Destinations.MyIconFrame.route)
                                }
                                .weight(1.0f))
                        MyDivider1()
                        Item(title = "${userViewModel.userInfo.concernCount ?: 0}",
                            title2 = "关注",
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Destinations.FanFrame.route + "/0")
                                }
                                .weight(1.0f))
                        MyDivider1()
                        Item(title = "${userViewModel.userInfo.fanCount ?: 0}",
                            title2 = "粉丝",
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Destinations.FanFrame.route + "/1")
                                }
                                .weight(1.0f))
                        MyDivider1()
                        Item(title = "${userViewModel.userInfo.payCount ?: 0}",
                            title2 = "付费",
                            modifier = Modifier
                                .clickable {

                                }
                                .weight(1.0f))
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
                        setting.value.userMenus.forEach { item ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .clickable {
                                        if (item.url.isNotBlank()) {
                                            Log.e(
                                                "MineScreen",
                                                "MineScreen: ${Destinations.TouGaoFrame.route}",
                                            )
                                            Log.e("MineScreen", "MineScreen: ${item.url}")
                                            navController.navigate(item.url)
                                        }
                                    }
                                    .width(80.dp)
                                    .padding(vertical = 16.dp, horizontal = 4.dp),
                            ) {
                                SoftIcon6(item.icon)
                                Text(text = item.title, fontSize = fontSize12)
                            }
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
                        Text(text = "浏览历史", fontSize = fontSize14)
                        RightIcon {
                            navController.navigate(Destinations.HistoryFrame.route)
                        }
                    }
                    HistoryItem(mineViewModel.list, onClick = { id ->
                        coroutineScope.launch {
                            navController.navigate(Destinations.AppDetailFrame.route + "/${id}")
                        }
                    })
                }
            }

        }
    }
}

@Composable
fun Item(title: String, title2: String, modifier: Modifier = Modifier) {
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
            text = title2, fontSize = MaterialTheme.typography.labelSmall.fontSize,
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
fun HistoryItem(list: List<HistoryEntity>, onClick: (id: Int) -> Unit) {
    list.forEach { item ->
        ListItem(
            modifier = Modifier.clickable {
                onClick(item.id)
            },
            colors = ListItemDefaults.colors(
                containerColor = Color.White
            ),
            headlineContent = {
                Text(text = "${item.name}", minLines = 1, overflow = TextOverflow.Ellipsis)
            },
            supportingContent = {
                Text(text = "${item.packageName}", minLines = 1, overflow = TextOverflow.Ellipsis)
            },
            leadingContent = {
                SoftIcon6_8("${item.logo}")
            },
            trailingContent = {
                Text(
                    text = "${CommonUtils.getDayInfo(item.updateDate)}",
                    minLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
        )
    }
}