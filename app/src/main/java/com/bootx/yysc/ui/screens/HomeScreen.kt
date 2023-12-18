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
import com.bootx.yysc.model.service.CenterBarData
import com.bootx.yysc.ui.components.AdData
import com.bootx.yysc.ui.components.CardTitle
import com.bootx.yysc.ui.components.ListItem1
import com.bootx.yysc.ui.components.ListItem2
import com.bootx.yysc.ui.components.ListItem3
import com.bootx.yysc.ui.components.Loading
import com.bootx.yysc.ui.components.MySearchBar
import com.bootx.yysc.ui.components.SoftIcon4
import com.bootx.yysc.ui.components.SwiperItem
import com.bootx.yysc.ui.components.ad.RequestBannerAd
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.theme.height4
import com.bootx.yysc.ui.theme.padding8
import com.bootx.yysc.ui.theme.shape8
import com.bootx.yysc.viewmodel.DownloadViewModel
import com.bootx.yysc.viewmodel.HomeViewModel
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
    homeViewModel: HomeViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
    downloadViewModel: DownloadViewModel = viewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        // 获取用户信息
        userViewModel.loadUserInfo(context)
        homeViewModel.load(context)
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
            if(homeViewModel.loading){
                Loading()
            }else{
                LazyColumn {
                    if (homeViewModel.homeData.carousels.isNotEmpty()) {
                        item {
                            SwiperItem(homeViewModel.homeData.carousels)
                        }
                    }
                    if (homeViewModel.homeData.centerBars.isNotEmpty()) {
                        item {
                            CenterBar(homeViewModel.homeData.centerBars, navigate = {
                                navController.navigate(it)
                            })
                        }
                    }
                    if (homeViewModel.homeData.todayCommentList.isNotEmpty()) {
                        item {
                            Column(
                                modifier = Modifier
                                    .padding(padding8)
                                    .clip(RoundedCornerShape(shape8)),
                            ) {
                                CardTitle(text = "好评如潮") {
                                    navController.navigate(Destinations.ListFrame.route + "/好评如潮/01")
                                }
                                ListItem1(homeViewModel.homeData.todayCommentList, onDownload = { id ->
                                    coroutineScope.launch {
                                        downloadViewModel.download(context, id)
                                    }
                                }, onClick = { id ->
                                    navController.navigate("${Destinations.AppDetailFrame.route}/${id}")
                                })
                            }
                        }
                    }
                    if (homeViewModel.homeData.activities.isNotEmpty()) {
                        item {
                            Column(
                                modifier = Modifier
                                    .padding(padding8)
                                    .clip(RoundedCornerShape(shape8)),
                            ) {
                                CardTitle(text = "最新活动", showIcon = false) {

                                }
                                AdData(homeViewModel.homeData.activities)
                            }
                        }
                    }
                    item{
                        RequestBannerAd(context = context)
                    }
                    if (homeViewModel.homeData.randomSeeList.isNotEmpty()) {
                        item {
                            Column(
                                modifier = Modifier
                                    .padding(padding8)
                                    .clip(RoundedCornerShape(shape8)),
                            ) {
                                CardTitle(text = "随心看看") {
                                    navController.navigate(Destinations.ListFrame.route + "/随心看看/2")
                                }
                                ListItem2(homeViewModel.homeData.randomSeeList, onDownload = { id ->
                                    navController.navigate("${Destinations.AppDetailFrame.route}/${id}")
                                }, onClick = { id ->
                                    navController.navigate("${Destinations.AppDetailFrame.route}/${id}")
                                })
                            }
                        }
                    }
                    if (homeViewModel.homeData.todayDownloadList.isNotEmpty()) {
                        item {
                            Column(
                                modifier = Modifier
                                    .padding(padding8)
                                    .clip(RoundedCornerShape(shape8)),
                            ) {
                                CardTitle(text = "今日下载") {
                                    navController.navigate(Destinations.ListFrame.route + "/今日下载/00")
                                }
                                ListItem3(list = homeViewModel.homeData.todayDownloadList, onDownload = { id ->
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
}

@Composable
fun CenterBar(list: List<CenterBarData>, navigate: (path: String) -> Unit) {
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