package com.bootx.yysc.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bootx.yysc.extension.onBottomReached
import com.bootx.yysc.model.entity.AppRankSearchEntity
import com.bootx.yysc.model.entity.SoftEntity
import com.bootx.yysc.ui.components.LeftIcon
import com.bootx.yysc.ui.components.RankIndex
import com.bootx.yysc.ui.components.SoftIcon6
import com.bootx.yysc.ui.components.TabRowScrollList
import com.bootx.yysc.ui.components.Tag
import com.bootx.yysc.ui.navigation.Destinations
import com.bootx.yysc.viewmodel.AppRankViewModel
import com.bootx.yysc.viewmodel.DownloadViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AppRankScreen(
    navController: NavHostController,
    appRankViewModel: AppRankViewModel = viewModel(),
    downloadViewModel: DownloadViewModel = viewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var selectIndex by remember {
        mutableStateOf(0)
    }
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        appRankViewModel.appRank(context)
    }

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        appRankViewModel.search(context, selectIndex, selectedTabIndex)
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)
    val lazyListState = rememberLazyListState()
    lazyListState.onBottomReached(buffer = 3) {
        coroutineScope.launch {
            refreshing = true
            appRankViewModel.search(context, selectIndex, selectedTabIndex)
            refreshing = false
        }
    }

    if (appRankViewModel.list.isNotEmpty()) {
        Scaffold(
            contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                expanded = !expanded
                            }
                        ) {
                            Text(
                                text = "${appRankViewModel.list[selectIndex].title}",
                                fontSize = MaterialTheme.typography.titleMedium.fontSize
                            )
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            offset = DpOffset(0.dp, (-56).dp)
                        ) {
                            appRankViewModel.list.mapIndexed { index, item ->
                                DropdownMenuItem(
                                    text = { Text(text = "${item.title}") },
                                    onClick = {
                                        selectIndex = index
                                        selectedTabIndex = 0
                                        expanded = false
                                    })
                            }
                        }
                    },
                    navigationIcon = {
                        LeftIcon {
                            navController.popBackStack()
                        }
                    },

                    )
            }
        ) {
            Surface(
                modifier = Modifier.padding(it)
            ) {
                if (appRankViewModel.list.isNotEmpty() && appRankViewModel.list[selectIndex].children.isNotEmpty()) {
                    TabRowScrollList(
                        tabs = appRankViewModel.list[selectIndex].children.map { item -> item.title },
                        selectedTabIndex = selectedTabIndex,
                        onClick = { index ->
                            selectedTabIndex = index
                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(0)
                                appRankViewModel.search(context, selectIndex, selectedTabIndex)
                            }
                        })
                }
                Box(
                    modifier = Modifier.padding(top = 60.dp)
                ) {
                    LazyColumn(
                        state = lazyListState,
                    ) {
                        itemsIndexed(appRankViewModel.list1) { index, soft ->
                            SoftItemDownload(rank = index, item = soft, onClick = {type->
                                if(type==0){
                                    navController.navigate(Destinations.AppDetailFrame.route+"/${soft.id}")
                                }else if(type == 1){
                                    coroutineScope.launch {
                                        downloadViewModel.download(context,soft.id)
                                    }
                                }
                            })

                        }
                    }
                    PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
                }
            }
        }
    }
}


@Composable
fun SoftItemDownload(
    rank: Int = -1,
    showDownload: Boolean = false,
    onClick:(type: Int)->Unit,
    item: AppRankSearchEntity
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .clickable {
                onClick(0);
            }
    ) {
        if (rank > -1) {
            RankIndex(index = rank + 1)
        }
        SoftIcon6(url = "${item.logo}")
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(text = "${item.name}")
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "",
                    Modifier.size(12.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${item.score}",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp
                )
                if (item.versionName != null && item.versionName.isNotEmpty()) {
                    Text(
                        text = item.versionName,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(start = 8.dp),
                        fontSize = 12.sp
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "",Modifier.size(12.dp))
                Tag(text = "星标")
                Text(
                    text = "${item.memo}", color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 12.sp
                )
            }
        }
        if (showDownload) {
            OutlinedButton(modifier = Modifier.padding(end = 16.dp), onClick = { onClick(1) }) {
                Text(text = "下载")
            }
        }
    }
}