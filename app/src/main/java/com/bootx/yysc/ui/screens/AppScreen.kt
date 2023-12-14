package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.extension.onBottomReached
import com.bootx.yysc.model.entity.CategoryEntity
import com.bootx.yysc.ui.components.Item3
import com.bootx.yysc.ui.components.Loading302
import com.bootx.yysc.ui.components.Loading404
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.util.SharedPreferencesUtils
import com.bootx.yysc.viewmodel.AppViewModel
import com.bootx.yysc.viewmodel.DownloadViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AppScreen(
    navController: NavHostController,
    vm: AppViewModel = viewModel(),
    downloadViewModel: DownloadViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var showDropdownMenu by remember {
        mutableStateOf(false)
    }
    var token = SharedPreferencesUtils(context).get("token")
    LaunchedEffect(Unit) {
        //获取分类列表
        vm.fetchList(token, 1, 100)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { TopBarTitle(text = "应用分类") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Destinations.SearchFrame.route)
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = "")
                    }
                    IconButton(onClick = {
                        showDropdownMenu = true
                    }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "")
                    }
                    OrderBy(showDropdownMenu, onClick = { orderBy ->
                        coroutineScope.launch {
                            vm.orderBy(token, orderBy)
                        }
                        showDropdownMenu = false
                    })
                }
            )
        }
    ) { contentPadding ->
        val refreshScope = rememberCoroutineScope()
        var refreshing by remember { mutableStateOf(false) }

        fun refresh() = refreshScope.launch {
            refreshing = true
            vm.reload(token)
            refreshing = false
        }

        val state = rememberPullRefreshState(refreshing, ::refresh)
        val lazyListState = rememberLazyListState()
        lazyListState.onBottomReached(buffer = 3) {
            coroutineScope.launch {
                vm.loadMore(token)
            }
        }
        Box(modifier = Modifier.padding(contentPadding)) {
            Row {
                if (vm.categoryLoading) {
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .fillMaxHeight()
                            .padding(top = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Loading302()
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .width(80.dp)
                            .padding(top = 16.dp)
                    ) {
                        vm.categories.forEachIndexed { _, category ->
                            item {
                                CategoryItem(
                                    category,
                                    category.id == vm.currentIndex
                                ) { currentIndex ->
                                    coroutineScope.launch {
                                        vm.updateCurrentIndex(token, currentIndex)
                                        lazyListState.animateScrollToItem(1)
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxHeight()
                        .padding(top = 16.dp)
                        .pullRefresh(state),
                ) {
                    if (vm.softListLoading) {
                        Loading404()
                    }
                    LazyColumn(
                        state = lazyListState,
                    ) {
                        items(vm.softList) { item ->
                            Item3(item, false, onDownload = { id ->
                                coroutineScope.launch {
                                    downloadViewModel.download(context, id)
                                }
                            }, onClick = { id ->
                                navController.navigate("${Destinations.AppDetailFrame.route}/$id")
                            })
                        }
                    }
                    PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.Center))
                }
            }

        }
    }

    /*Dialog(onDismissRequest = { *//*TODO*//* }) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp).alpha(0.2f).background(Color(0xff3f3f3f)),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "aaaa")
        }
    }*/
}

@Composable
fun CategoryItem(category: CategoryEntity, selected: Boolean, click: (id: Int) -> Unit) {
    Button(
        contentPadding = PaddingValues(horizontal = 4.dp),
        modifier = Modifier.width(80.dp),
        onClick = {
            click(category.id)
        },
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 18.dp,
            bottomEnd = 18.dp,
            bottomStart = 0.dp,
        ),
        colors = ButtonDefaults.textButtonColors(
            disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        enabled = !selected,
    ) {
        Text(
            text = category.name,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize12,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun OrderBy(showDropdownMenu: Boolean = false, onClick: (orderBy: String) -> Unit) {
    data class Item(
        val key: String,
        val title: String,
    )

    var index by remember {
        mutableStateOf("0")
    }

    val list = listOf(
        Item(
            key = "1",
            title = "默认排序"
        ),
        Item(
            key = "3",
            title = "最新发布"
        ),
        Item(
            key = "4",
            title = "最近活跃"
        ),
        Item(
            key = "5",
            title = "最多评价"
        ),
        Item(
            key = "6",
            title = "最多投币"
        ),
        Item(
            key = "7",
            title = "下载量"
        ),
    )
    DropdownMenu(
        offset = DpOffset(0.dp, (-48).dp),
        expanded = showDropdownMenu,
        onDismissRequest = {},
        content = {
            list.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onClick(item.key)
                        index = item.key
                    },
                    text = {
                        ListItem(
                            headlineContent = {
                                Text(text = item.title)
                            },
                            trailingContent = {
                                Checkbox(checked = item.key === index, onCheckedChange = {
                                    onClick(item.key)
                                })
                            }
                        )
                    }
                )
            }
        },
    )
}