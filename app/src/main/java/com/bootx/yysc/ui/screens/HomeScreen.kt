package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.config.Config
import com.bootx.yysc.model.entity.ActivityEntity
import com.bootx.yysc.model.entity.HomeCenterBar
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.components.AdData
import com.bootx.yysc.ui.components.CardTitle
import com.bootx.yysc.ui.components.ListItem1
import com.bootx.yysc.ui.components.ListItem2
import com.bootx.yysc.ui.components.ListItem3
import com.bootx.yysc.ui.components.MySearchBar
import com.bootx.yysc.ui.components.SoftIcon4
import com.bootx.yysc.ui.components.SwiperItem
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.theme.height4
import com.bootx.yysc.ui.theme.padding8
import com.bootx.yysc.ui.theme.shape8
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.viewmodel.CarouselViewModel
import com.bootx.yysc.viewmodel.DownloadViewModel
import com.bootx.yysc.viewmodel.HomeViewModel
import com.bootx.yysc.viewmodel.SoftViewModel
import com.bootx.yysc.viewmodel.UserViewModel
import kotlinx.coroutines.launch

data class ItemList(
    val icon: Int,
    val title: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("InvalidColorHexValue")
@Composable
fun HomeScreen(
    navController: NavHostController,
    carouselViewModel: CarouselViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel(),
    softViewModel: SoftViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
    downloadViewModel: DownloadViewModel = viewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val todayDownloadList = remember {
        mutableStateOf(listOf<SoftEntity>())
    }

    val todayCommentList = remember {
        mutableStateOf(listOf<SoftEntity>())
    }

    val randomList = remember {
        mutableStateOf(listOf<SoftEntity>())
    }

    val homeCenterBarList = remember {
        mutableStateOf(listOf<HomeCenterBar>())
    }

    val activityList = remember {
        mutableStateOf(listOf<ActivityEntity>())
    }
    val sharedPreferencesUtils = SharedPreferencesUtils(context)
    val token = sharedPreferencesUtils.get("token")
    LaunchedEffect(Unit) {
        // 获取用户信息
        userViewModel.loadUserInfo(context)
        //获取轮播数据
        carouselViewModel.fetchList(token);
        // 中间工具栏
        homeCenterBarList.value = homeViewModel.homeCenterBar(token);
        // 活动
        activityList.value = homeViewModel.activity(token);

        // 今日下载
        todayDownloadList.value = softViewModel.orderBy(token, 1, 30, "00")
        // 今日评论
        todayCommentList.value = softViewModel.orderBy(token, 1, 30, "01")
        //随心看
        randomList.value = softViewModel.orderBy(token, 1, 30, "2")
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                MySearchBar()
            }, navigationIcon = {
                if (userViewModel.userInfo.id > 0) {
                    SoftIcon4(
                        url = "${userViewModel.userInfo.avatar}?x-oss-process=style/size_100",
                        modifier = Modifier.clickable {
                            navController.navigate(Destinations.MainFrame.route+"/4")
                        })
                } else {
                    SoftIcon4(
                        url = "${Config.imageUrl}avatar/0.png?x-oss-process=style/size_100",
                        modifier = Modifier.clickable {
                            navController.navigate(Destinations.LoginFrame.route)
                        })
                }
            }, actions = {
                Box(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Destinations.DownloadManagerFrame.route)
                        }
                ) {
                    Box(
                        modifier = Modifier.padding(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Download, contentDescription = ""
                        )
                    }
                    Box(
                        Modifier.align(Alignment.TopEnd)
                    ) {
                        Text(text = "2", fontSize = MaterialTheme.typography.bodySmall.fontSize)
                    }
                }

                Icon(modifier = Modifier.clickable {
                    navController.navigate(Destinations.NotifyFrame.route)
                }, imageVector = Icons.Default.Notifications, contentDescription = "")
            })
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primary),
        ) {
            LazyColumn {
                if (carouselViewModel.carousels.isNotEmpty()) {
                    item {
                        if (carouselViewModel.listLoaded) {
                            SwiperItem(carouselViewModel.carousels)
                        }
                    }
                }
                if (homeCenterBarList.value.isNotEmpty()) {
                    item {
                        CenterBar(homeCenterBarList.value, navigate = {
                            navController.navigate(it)
                        })
                    }
                }
                if (todayCommentList.value.isNotEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .padding(padding8)
                                .clip(RoundedCornerShape(shape8)),
                        ) {
                            CardTitle(text = "好评如潮") {
                                navController.navigate(Destinations.ListFrame.route + "/好评如潮/01")
                            }
                            ListItem1(todayCommentList.value, onDownload = { id ->
                                coroutineScope.launch {
                                    downloadViewModel.download(context, id)
                                }
                            }, onClick = { id ->
                                navController.navigate("${Destinations.AppDetailFrame.route}/${id}")
                            })
                        }
                    }
                }
                if (activityList.value.isNotEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .padding(padding8)
                                .clip(RoundedCornerShape(shape8)),
                        ) {
                            CardTitle(text = "最新活动", showIcon = false) {

                            }
                            AdData(activityList.value)
                        }
                    }
                }
                if (randomList.value.isNotEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .padding(padding8)
                                .clip(RoundedCornerShape(shape8)),
                        ) {
                            CardTitle(text = "随心看看") {
                                navController.navigate(Destinations.ListFrame.route + "/随心看看/2")
                            }
                            ListItem2(randomList.value, onDownload = { id ->
                                navController.navigate("${Destinations.AppDetailFrame.route}/${id}")
                            }, onClick = { id ->
                                navController.navigate("${Destinations.AppDetailFrame.route}/${id}")
                            })
                        }
                    }
                }
                if (todayDownloadList.value.isNotEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .padding(padding8)
                                .clip(RoundedCornerShape(shape8)),
                        ) {
                            CardTitle(text = "今日下载") {
                                navController.navigate(Destinations.ListFrame.route + "/今日下载/00")
                            }
                            ListItem3(list = todayDownloadList.value, onDownload = { id ->
                                coroutineScope.launch {
                                    downloadViewModel.download(context, id)
                                }
                            }, onClick = { id ->
                                navController.navigate("${Destinations.AppDetailFrame.route}/${id}")
                            })
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}


@Composable
fun CenterBar(list: List<HomeCenterBar>, navigate: (path: String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .height(90.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        list.forEachIndexed { _, itemList ->
            Column(
                modifier = Modifier
                    .weight(1.0f)
                    .fillMaxHeight()
                    .clickable {
                        when (itemList.name) {
                            "签到" -> navigate(Destinations.SignInFrame.route)
                            "群组" -> navigate(Destinations.QunZuFrame.route)
                            "福利" -> navigate(Destinations.FuLiFrame.route)
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                AsyncImage(
                    modifier = Modifier.size(48.dp),
                    model = itemList.image,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(height4))
                Text(
                    text = itemList.name,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}