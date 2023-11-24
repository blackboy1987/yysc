package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.extension.onBottomReached
import com.bootx.yysc.viewmodel.PointLogViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MyIconListScreen(navController: NavHostController,pointLogViewModel: PointLogViewModel= viewModel()) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        //获取分类列表
        pointLogViewModel.list(1, 20)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "硬币明细")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                }
            )
        }
    ) {contentPadding->
        val refreshScope = rememberCoroutineScope()
        var refreshing by remember { mutableStateOf(false) }

        fun refresh() = refreshScope.launch {
            Log.e("refresh", "refresh: ", )
            refreshing = true
            pointLogViewModel.list(1, 20)
            refreshing = false
        }

        val state = rememberPullRefreshState(refreshing, ::refresh)
        val lazyListState = rememberLazyListState()
        lazyListState.onBottomReached(buffer = 3) {
            coroutineScope.launch {
                pointLogViewModel.list(pointLogViewModel.pageNumber, 20)
                Log.e("loadMore", "loadMore: ", )
            }
        }
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .pullRefresh(state)
        ) {
            if(pointLogViewModel.loading){
                CircularProgressIndicator()
            }else{
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = lazyListState,
                ){
                    items(pointLogViewModel.list){
                        ListItem(
                            headlineContent = {
                                Text(text = it.memo)
                            },
                            leadingContent = {
                                AsyncImage(
                                    model = "https://ts2.cn.mm.bing.net/th?id=ORMS.6cc25f7bc7c9600ec74736b8442771f7&pid=Wdp&w=612&h=304&qlt=90&c=1&rs=1&dpr=1.5&p=0",
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                )
                            },
                            trailingContent = {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(
                                        text = "+${it.point}",
                                        fontSize=MaterialTheme.typography.titleLarge.fontSize,
                                        color = MaterialTheme.colorScheme.primary,
                                    )
                                    Text(
                                        text = it.seconds,
                                        fontSize=MaterialTheme.typography.titleSmall.fontSize,
                                        color = MaterialTheme.colorScheme.primary,
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
}
