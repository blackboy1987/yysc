package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.ModalBottomSheetDefaults
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bootx.yysc.extension.onBottomReached
import com.bootx.yysc.ui.theme.fontSize14
import kotlinx.coroutines.launch

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun TouGaoListScreen(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        Log.e("refresh", "refresh: ")
        refreshing = true
        //reload()
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)
    val lazyListState = rememberLazyListState()
    lazyListState.onBottomReached(buffer = 3) {
        coroutineScope.launch {
            //loadMore()
        }
    }
    val tabs = listOf("全部", "已上架", "审核中", "未通过", "草稿箱")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 8.dp),
                title = {
                    Column {
                        Text(
                            text = "应用投稿",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = "欢迎投稿",
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                },
                navigationIcon = {
                    Icon(modifier = Modifier.clickable {

                    }, imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "")
                },
                actions = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                coroutineScope.launch {
                    if (sheetState.isVisible) sheetState.hide() else sheetState.show()
                }
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay,
    ) {
        Surface(
            modifier = Modifier.padding(it)
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                ) {
                    Item(title = "15", title2 = "总下载", modifier = Modifier.weight(1.0f))
                    MyDivider1()
                    Item(title = "15", title2 = "评价数", modifier = Modifier.weight(1.0f))
                    MyDivider1()
                    Item(title = "15", title2 = "五星好评", modifier = Modifier.weight(1.0f))
                    MyDivider1()
                    Item(title = "15", title2 = "投币数量", modifier = Modifier.weight(1.0f))
                }
                SecondaryTabRow(
                    divider = @Composable {

                    },
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier
                        .focusRestorer()
                        .padding(8.dp),
                    tabs = {
                        tabs.forEachIndexed { index, item ->
                            Tab(selected = selectedTabIndex == index, onClick = {
                                selectedTabIndex = index
                                coroutineScope.launch {
                                    lazyListState.animateScrollToItem(1)
                                }
                            }) {
                                Text(
                                    text = item,
                                    fontSize = fontSize14,
                                    modifier = Modifier.padding(4.dp),
                                )
                            }
                        }
                    }
                )
                Box(
                    modifier = Modifier
                        .pullRefresh(state)
                        .padding(8.dp)
                ) {
                    if (refreshing) {
                        CircularProgressIndicator()
                    } else {
                        AnimatedContent(
                            targetState = selectedTabIndex,
                            label = "AnimatedContent",
                            transitionSpec = {
                                (slideInHorizontally { width -> width } + fadeIn()).togetherWith(
                                    slideOutVertically { height -> -height } + fadeOut())
                            },
                        ) {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                state = lazyListState,
                            ) {
                                items(100) {

                                    Text(text = "aa $it")

                                }
                            }
                        }
                        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
    ModalBottomSheetLayout(
        sheetElevation = 0.dp,
        sheetState = sheetState,
        sheetContent = {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = " sheetContent title")
            Text(text = " sheetContent content")
            Text(text = " sheetContent content2")
            Text(text = " sheetContent content3")
            Text(text = " sheetContent content4")
            Text(text = " sheetContent content5")
            Text(text = " sheetContent content6")
            Text(text = " sheetContent content7")
            Text(text = " sheetContent content8")
            Text(text = " sheetContent content9")
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                coroutineScope.launch {
                    sheetState.hide()
                }
            }) {
                Text(text = "取消")
            }
        },
        modifier = Modifier.fillMaxWidth(),
        sheetShape = RoundedCornerShape(4.dp),
        scrimColor = ModalBottomSheetDefaults.scrimColor
    ) {

    }
}
