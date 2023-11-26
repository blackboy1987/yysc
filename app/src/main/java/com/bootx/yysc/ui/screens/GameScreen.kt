package com.bootx.yysc.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.extension.onBottomReached
import com.bootx.yysc.model.entity.CategoryEntity
import com.bootx.yysc.ui.components.SoftItem
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.viewmodel.AppViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun GameScreen(navController: NavHostController, vm: AppViewModel = viewModel()) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        //获取分类列表
        vm.fetchList(1, 100)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("游戏类别") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Destinations.SearchFrame.route)
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = "")
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "")
                    }
                }
            )
        }
    ) { contentPadding ->
        val refreshScope = rememberCoroutineScope()
        var refreshing by remember { mutableStateOf(false) }

        fun refresh() = refreshScope.launch {
            refreshing = true
            vm.reload()
            refreshing = false
        }

        val state = rememberPullRefreshState(refreshing, ::refresh)
        val lazyListState = rememberLazyListState()
        lazyListState.onBottomReached(buffer = 3) {
            coroutineScope.launch {
                vm.loadMore()
            }
        }
        Box(modifier = Modifier.padding(contentPadding)) {
            Row {
                LazyColumn(
                    modifier = Modifier
                        .width(80.dp)
                        .padding(top = 16.dp)
                ) {
                    if (vm.listLoaded) {
                        vm.categories.forEachIndexed { index, category ->
                            item {
                                CategoryItem(
                                    category,
                                    category.id == vm.currentIndex
                                ) { currentIndex ->
                                    coroutineScope.launch {
                                        lazyListState.animateScrollToItem(1)
                                        vm.updateCurrentIndex(currentIndex)
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
                        .padding(top = 16.dp)
                        .pullRefresh(state)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        state = lazyListState,
                    ) {
                        if (!refreshing) {
                            items(vm.softList) { soft ->
                                SoftItem(soft, false, onDownload = { download(context,soft) })
                            }
                        }
                    }
                    PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.Center))
                }
            }
        }
    }
}