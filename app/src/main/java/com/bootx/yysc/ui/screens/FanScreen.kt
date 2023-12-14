package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.extension.onBottomReached
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.SoftIcon6
import com.bootx.yysc.ui.components.Tag
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.viewmodel.FanViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class
)
@Composable
fun FanScreen(
    navController: NavHostController,
    type: Int,
    fanViewModel: FanViewModel = viewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf("我的关注", "我的粉丝")
    var selectedTabIndex by remember { mutableIntStateOf(type) }

    LaunchedEffect(Unit) {
        // 加载粉丝列表
        fanViewModel.list(SharedPreferencesUtils(context).get("token"), selectedTabIndex)
    }


    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        fanViewModel.list(SharedPreferencesUtils(context).get("token"), selectedTabIndex)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)
    val lazyListState = rememberLazyListState()
    lazyListState.onBottomReached(buffer = 3) {
        coroutineScope.launch {
            fanViewModel.loadMore(SharedPreferencesUtils(context).get("token"), selectedTabIndex)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarTitle(text = "我的关注和粉丝${selectedTabIndex}")
                },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column {
                SecondaryTabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier.focusRestorer(),
                    tabs = {
                        tabs.forEachIndexed { index, item ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = {
                                    selectedTabIndex = index
                                    coroutineScope.launch {
                                        fanViewModel.list(
                                            SharedPreferencesUtils(context).get("token"),
                                            selectedTabIndex
                                        )
                                    }
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
                Box(
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxHeight()
                        .padding(top = 16.dp)
                        .pullRefresh(state),
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        state = lazyListState,
                    ) {
                        items(fanViewModel.list) { item ->
                            ListItem(
                                leadingContent = {
                                    SoftIcon6(url = "${item.avatar}")
                                },
                                headlineContent = {
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Text(
                                            text = "${item.username}",
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.padding(end = 4.dp)
                                        )
                                        Tag(text = "${item.rankName}")
                                    }
                                },
                                supportingContent = { Text(text = "坐拥${item.point}枚硬币") },
                                trailingContent = {
                                    if (item.count > 0) {
                                        OutlinedButton(onClick = {
                                            coroutineScope.launch {
                                                fanViewModel.delete(
                                                    SharedPreferencesUtils(context).get(
                                                        "token"
                                                    ), item.id, selectedTabIndex
                                                )
                                            }
                                        }) {
                                            Text(text = "取关")
                                        }
                                    } else {
                                        Button(onClick = {
                                            coroutineScope.launch {
                                                fanViewModel.add(
                                                    SharedPreferencesUtils(context).get(
                                                        "token"
                                                    ), item.id, selectedTabIndex
                                                )
                                            }
                                        }) {
                                            Text(text = "关注")
                                        }
                                    }
                                }
                            )
                        }
                    }
                    PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.Center))
                }
            }
        }
    }
}
