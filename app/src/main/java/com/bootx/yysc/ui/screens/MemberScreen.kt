package com.bootx.yysc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.SoftIcon8
import com.bootx.yysc.ui.components.Tag
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.viewmodel.MemberViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MemberScreen(
    navController: NavHostController,
    id: String,
    memberViewModel: MemberViewModel = viewModel()
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    var showDropdownMenu by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        memberViewModel.loadUserInfo(context, "1")
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = "https://img.zcool.cn/community/014e3f55444ef80000019ae9290dd5.jpg",
                contentScale = ContentScale.FillBounds,
                contentDescription = ""
            )
        }
        Box(Modifier.background(Color.Transparent)) {
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ), title = { /*TODO*/ }, navigationIcon = {
                        LeftIcon {
                            navController.popBackStack()
                        }
                    }, actions = {
                        IconButton(onClick = {
                            navController.navigate(Destinations.SearchFrame.route)
                        }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "")
                        }
                        IconButton(onClick = {

                        }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                        }
                    })
                }
            ) {
                Surface(
                    Modifier.padding(it),
                    contentColor = Color.Transparent,
                ) {
                    LazyColumn(
                        Modifier
                            .background(Color.Transparent)
                            .fillMaxWidth()
                    ) {
                        item {
                            ListItem(
                                modifier = Modifier
                                    .background(Color.Transparent),
                                headlineContent = { /*TODO*/ },
                                leadingContent = {
                                    SoftIcon8(
                                        url = "${memberViewModel.memberInfo.avatar}",
                                    )
                                },
                                trailingContent = {
                                    OutlinedButton(onClick = { /*TODO*/ }) {
                                        Text(text = "取消关注")
                                    }
                                }
                            )
                        }
                        item {
                            ListItem(
                                modifier = Modifier
                                    .background(Color.Transparent),
                                headlineContent = {
                                    Text(text = "${memberViewModel.memberInfo.username}")
                                    Tag(text = "${memberViewModel.memberInfo.rankName}")
                                },
                            )
                        }
                        item {
                            ListItem(
                                modifier = Modifier
                                    .background(Color.Transparent),
                                headlineContent = {
                                    Text(text = "注册日期：${memberViewModel.memberInfo.createdDate}")
                                },
                            )
                        }
                        item {
                            ListItem(
                                modifier = Modifier
                                    .background(Color.Transparent),
                                headlineContent = {
                                    Text(text = "${memberViewModel.memberInfo.memo}")
                                },
                            )
                        }
                        item {
                            ListItem(
                                modifier = Modifier
                                    .background(Color.Transparent),
                                headlineContent = {
                                    Text(text = "${memberViewModel.memberInfo.point} 硬币")
                                },
                            )
                        }
                        item {
                            ListItem(
                                modifier = Modifier
                                    .background(Color.Transparent),
                                headlineContent = {
                                    Text(text = "${memberViewModel.memberInfo.concernCount} 关注")
                                },
                            )
                        }
                        item {
                            ListItem(
                                modifier = Modifier
                                    .background(Color.Transparent),
                                headlineContent = {
                                    Text(text = "${memberViewModel.memberInfo.fanCount} 粉丝")
                                },
                            )
                        }
                        item {
                            val tabs = listOf("应用", "评分","讨论","帖子","粉丝","关注")
                            SecondaryTabRow(selectedTabIndex = selectedTabIndex,modifier = Modifier.focusRestorer(),
                                tabs = {
                                    tabs.forEachIndexed { index, item ->
                                        Tab(
                                            selected = selectedTabIndex == index,
                                            onClick = {
                                                selectedTabIndex = index
                                            }) {
                                            Text(
                                                text = item,
                                                fontSize = fontSize14,
                                                modifier = Modifier.padding(16.dp),
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }

}