package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.config.Config
import com.bootx.yysc.extension.onBottomReached
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.SoftIcon6
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.viewmodel.PointLogViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MyIconListScreen(
    navController: NavHostController,
    pointLogViewModel: PointLogViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    //val storeManager: StoreManager = StoreManager(LocalContext.current)
    //val token = storeManager.getToken().collectAsState(initial = Config.initToken).value
    LaunchedEffect(Unit) {
        //获取积分记录列表
        pointLogViewModel.list(context, 1, 20)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarTitle(text = "硬币明细")
                },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) { contentPadding ->
        val refreshScope = rememberCoroutineScope()
        var refreshing by remember { mutableStateOf(false) }

        fun refresh() = refreshScope.launch {
            refreshing = true
            pointLogViewModel.list(context, 1, 20)
            refreshing = false
        }

        val state = rememberPullRefreshState(refreshing, ::refresh)
        val lazyListState = rememberLazyListState()
        lazyListState.onBottomReached(buffer = 3) {
            if (pointLogViewModel.hasMore && !pointLogViewModel.loading) {
                coroutineScope.launch {
                    pointLogViewModel.list(context, pointLogViewModel.pageNumber, 20)
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .pullRefresh(state)
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState,
            ) {
                itemsIndexed(pointLogViewModel.list) { index,item->
                    ListItem(
                        headlineContent = {
                            Text(text = "${index}:${item.memo}")
                        },
                        leadingContent = {
                            SoftIcon6(url = "${Config.imageUrl}avatar/100.png")
                        },
                        trailingContent = {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = "+${item.point}",
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    color = MaterialTheme.colorScheme.primary,
                                )
                                Text(
                                    text = item.seconds,
                                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                )
                            }
                        }
                    )
                }
            }
            PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.Center))
        }
    }
}
