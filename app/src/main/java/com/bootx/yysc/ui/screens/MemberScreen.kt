package com.bootx.yysc.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.extension.onScroll
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.SoftIcon6
import com.bootx.yysc.ui.components.SoftIcon8
import com.bootx.yysc.ui.components.SoftIcon8_8
import com.bootx.yysc.ui.components.SoftItem
import com.bootx.yysc.ui.components.TabRowList
import com.bootx.yysc.ui.components.Tag
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.viewmodel.MemberViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun MemberScreen(
    navController: NavHostController,
    id: String,
    memberViewModel: MemberViewModel = viewModel()
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var showTopBar by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    var showDropdownMenu by remember {
        mutableStateOf(false)
    }
    val lazyListState = rememberLazyListState()
    lazyListState.onScroll(callback = { index ->
        showTopBar = index > 0
    })
    LaunchedEffect(Unit) {
        // memberViewModel.loadUserInfo(context, "1")
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                if (showTopBar) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        SoftIcon6(url = "https://bootx-tuchuang.oss-cn-hangzhou.aliyuncs.com/avatar/5.png")
                        Text(text = "blackboy", modifier = Modifier.padding(start = 16.dp))
                    }
                }
            }, navigationIcon = {
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
        ) {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                state = lazyListState,
            ) {
                item {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        SoftIcon8_8(url = "https://bootx-tuchuang.oss-cn-hangzhou.aliyuncs.com/avatar/5.png")
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            OutlinedButton(onClick = { /*TODO*/ }) {
                                Text(text = "取消关注")
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.WavingHand,
                                    contentDescription = ""
                                )
                            }
                        }

                    }
                }
                item {
                    Row(
                        Modifier.padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "blackboy", modifier = Modifier.padding(end = 16.dp))
                        Tag(text = "lv.1")
                    }
                }
                item {
                    Text(
                        text = "注册日期：2023年12月12日",
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                item {
                    Text(
                        text = "打死了开发安达斯鲁迪斯科浪费静安寺士大夫卢卡斯来到喀山离开",
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                item {
                    Row(
                        modifier = Modifier.padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                ) {
                                    append("92")
                                }
                                withStyle(
                                    style = SpanStyle(

                                    )
                                ) {
                                    append(" 硬币")
                                }
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                ) {
                                    append("92")
                                }
                                withStyle(
                                    style = SpanStyle(

                                    )
                                ) {
                                    append(" 关注")
                                }
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                ) {
                                    append("92")
                                }
                                withStyle(
                                    style = SpanStyle(

                                    )
                                ) {
                                    append(" 粉丝")
                                }
                            }
                        )
                    }
                }
                stickyHeader {
                    val tabs = listOf("应用", "评分", "讨论", "帖子", "粉丝", "关注")
                    TabRowList(
                        tabs = tabs,
                        selectedTabIndex = selectedTabIndex,
                        onClick = { index ->
                            selectedTabIndex = index
                        })
                }
                if(selectedTabIndex==0){
                    items(100) {
                        SoftItem(SoftEntity())
                    }
                }else{
                    items(100) {index->
                       Text(text = "${index}")
                    }
                }

            }
        }
    }

}