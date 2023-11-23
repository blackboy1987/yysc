package com.bootx.yysc.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bootx.yysc.extension.onBottomReached
import com.bootx.yysc.extension.onScroll
import com.bootx.yysc.ui.components.SoftItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AppDetailScreen(navController: NavHostController, id: String) {
    val lazyListState = rememberLazyListState()
    val showTitle = remember {
        mutableStateOf(false)
    }
    lazyListState.onScroll() {
        Log.e("AppDetailScreen", "onScroll: $it", )
        if(it>=1){
            showTitle.value = true
        }else if(showTitle.value){
            showTitle.value = false
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { if(showTitle.value) Text(text = "abc") },
                navigationIcon = {
                    Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "")
                },
                actions = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                    Icon(imageVector = Icons.Filled.Dehaze,contentDescription = "")
                }
            )
        }
    ) {

        Box(
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                state = lazyListState,
            ) {
                item{
                    ListItem(
                        headlineContent = {
                            Text(
                                text = "奇妙搜搜",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        supportingContent = {
                            Text(
                                text = "奇妙搜搜",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        leadingContent = {
                            AsyncImage(
                                modifier = Modifier.size(60.dp),
                                model = "https://android-artworks.25pp.com/fs08/2023/11/06/10/110_f79419762ee11b23c04ea2e487adc0ba_con_130x130.png",
                                contentDescription = ""
                            )
                        }
                    )
                }
                item{
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = "9.7")
                            Text(text = "544条评论")
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = "7.79MB")
                            Text(text = "大小")
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = "10.86万")
                            Text(text = "下载")
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = "1841")
                            Text(text = "748人投币")
                        }
                    }
                }
                item{
                    val tabs = listOf("详情", "讨论", "版本")
                    var selectedTabIndex by remember { mutableIntStateOf(0) }
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
                                }) {
                                    Text(
                                        text = item,
                                        modifier = Modifier.padding(4.dp),
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