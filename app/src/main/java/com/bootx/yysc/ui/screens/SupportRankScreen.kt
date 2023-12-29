package com.bootx.yysc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.extension.onBottomReached
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.RankIndex
import com.bootx.yysc.ui.components.SoftIcon6
import com.bootx.yysc.ui.components.Tag
import com.bootx.yysc.ui.components.TopBarTitle
import com.bootx.yysc.ui.theme.fontSize12
import com.bootx.yysc.ui.theme.fontSize14
import com.bootx.yysc.viewmodel.SupportRankViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SupportRankScreen(navController: NavHostController,supportRankViewModel: SupportRankViewModel = viewModel()) {
    val context = LocalContext.current
    LaunchedEffect(Unit){
        supportRankViewModel.rank(context,1)
    }
    val coroutineScope = rememberCoroutineScope()
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        supportRankViewModel.rank(context,1)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)
    val lazyListState = rememberLazyListState()
    lazyListState.onBottomReached(buffer = 3) {
        coroutineScope.launch {
            refreshing = true
            supportRankViewModel.rank(context,1)
            refreshing = false
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopBarTitle(
                        text = "激励排行榜",
                    )
                },
                navigationIcon = {
                    LeftIcon {
                        navController.popBackStack()
                    }
                },

                )
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                state = lazyListState,
            ) {
                itemsIndexed(supportRankViewModel.list){index,item->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.width(16.dp))
                        RankIndex(index = index+1)
                        ListItem(
                            headlineContent = {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(text = "${item.username}", fontSize = fontSize14)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Tag(text = "${item.rankName}")
                                }
                            },
                            supportingContent = {
                                Text(
                                    text = "支持了${item.times}次",
                                    fontSize = fontSize12,
                                    color = Color(0xFF9b9b9b)
                                )
                            },
                            leadingContent = {
                                SoftIcon6(url = "${item.avatar}")
                            },
                        )
                    }
                }
            }
            PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
        }
    }
}
